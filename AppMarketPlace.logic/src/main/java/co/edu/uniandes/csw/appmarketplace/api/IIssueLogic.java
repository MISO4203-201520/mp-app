/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.api;

import co.edu.uniandes.csw.appmarketplace.dtos.IssueDTO;
import java.util.List;

/**
 *
 * @author af.decastro879
 */
public interface IIssueLogic {
    public int countIssues();
    public List<IssueDTO> getIssues(Integer page, Integer maxRecords);
    public IssueDTO getIssue(Long id);
    public IssueDTO createIssue(IssueDTO dto);
    public IssueDTO updateIssue(IssueDTO dto);
    public void deleteIssue(Long id);
    public List<IssueDTO> getIssuesByAppId(String appId);
    public void resolveIssue(Long id);
    
}
