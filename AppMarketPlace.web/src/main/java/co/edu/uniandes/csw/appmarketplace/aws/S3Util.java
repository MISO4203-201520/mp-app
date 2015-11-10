package co.edu.uniandes.csw.appmarketplace.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author djimenez
 */
public class S3Util {

    private static final String BUCKET_NAME = System.getenv("APPOTECA_BUCKET_NAME");;
    private static final String APP_ACCESS_KEY_ID = System.getenv("APPOTECA_ACCESS_KEY_ID");
    private static final String APP_SECRET_ACCESS_KEY = System.getenv("APPOTECA_SECRET_ACCESS_KEY");
    public static final String IMAGE_PATH = "https://s3-us-west-2.amazonaws.com/appoteca/images/";
    public static final String VIDEO_PATH = "https://s3-us-west-2.amazonaws.com/appoteca/videos/";
    public static final String SOURCE_PATH = "https://s3-us-west-2.amazonaws.com/appoteca/sources/";

    /**
     * Upload a single file in AWS S3
     * 
     * @param prefix it could be "videos/" or "images/" or "/sources"
     * @param file local file to upload in AWS S3
     * @param appId apllication id
     */
    public static void uploadFile(String prefix, File file, Long appId) {
        AWSCredentials credentials = new BasicAWSCredentials(
                APP_ACCESS_KEY_ID, APP_SECRET_ACCESS_KEY);
        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);

        List<S3ObjectSummary> s3Objects = s3client.listObjects(
                new ListObjectsRequest().withBucketName(BUCKET_NAME).withPrefix(prefix + appId.toString()))
                .getObjectSummaries();

        // Creating new folder named video or image id, if this folder does not exist
        // BUCKET_NAME/videos/:appId/videofile or BUCKET_NAME/images/:appid/imagefile
        if (s3Objects == null || s3Objects.isEmpty()) {
            createFolder(prefix, appId.toString(), s3client);
        }

        // upload file to folder and set it to public
        s3client.putObject(
                new PutObjectRequest(BUCKET_NAME, prefix + appId + "/" + file.getName(),
                        file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    /**
     * Create a new folder in AWS S3
     * 
     * @param prefix it could be "videos/" or "images/" or "/sources"
     * @param folderName folder to create in AWS S3
     * @param client S3 client connection based on AWS credentials
     */
    private static void createFolder(String prefix, String folderName, AmazonS3 client) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME,
                prefix + folderName + "/", emptyContent, metadata);
        // send request to S3 to create folder
        client.putObject(putObjectRequest);
    }
}