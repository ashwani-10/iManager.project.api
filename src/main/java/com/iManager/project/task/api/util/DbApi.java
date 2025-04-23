package com.iManager.project.task.api.util;

import com.iManager.project.task.api.requestDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.UUID;

@Service
public class DbApi extends ApiUtilImpl{
    @Value("${service.db.url}")
    private String dbUrl;
    @Autowired
    RestTemplate restTemplate;


    public Object createProject(ProjectRequestDTO request,String userEmail){
        System.out.println("Inside project creating method");
        try {
            String endpoint = dbUrl+"/db/api/project/create/"+userEmail;
            ResponseEntity response = makePostCall(request,endpoint,"",new HashMap<>());
            System.out.println("posted project successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed posting project");
            throw new RuntimeException("failed posting project");
        }
    }

    public void deleteProject(UUID projectId){
        System.out.println("Inside project creating method");
        try {
            String url = dbUrl+"/db/api/project/delete/"+projectId;
            RequestEntity request = RequestEntity.get(url).build();
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.DELETE,request, Object.class);
            System.out.println("posted Deleted successfully");
        }catch (Exception e){
            System.out.println("failed Deleting project");
            throw new RuntimeException("failed Deleting project");
        }
    }

    public ResponseEntity updateProject(ProjectRequestDTO requestDTO){
        System.out.println("Inside project creating method");
        try {
            String url = dbUrl+"/db/api/project/update";
            RequestEntity request = RequestEntity.post(url).body(requestDTO);
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.PUT,request, Object.class);
            System.out.println("posted Updated successfully");
            return response;
        }catch (Exception e){
            System.out.println("failed Deleting project");
            throw new RuntimeException("failed Deleting project");
        }
    }

    public Object getProject(UUID orgID){
        System.out.println("Inside project creating method");
        try {
            String url = dbUrl+"/db/api/project/get/"+orgID;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("posted Deleted successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Deleting project");
            throw new RuntimeException("failed Deleting project");
        }
    }

    public Object createSubProject(SubProjectReqDTO request,String userEmail){
        System.out.println("Inside project creating method");
        try {
            String endpoint = dbUrl+"/db/api/subProject/create/"+userEmail;
            ResponseEntity response = makePostCall(request,endpoint,"",new HashMap<>());
            System.out.println("posted project successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed posting project");
            throw new RuntimeException("failed posting project");
        }
    }

    public void deleteSubProject(UUID subProjectId){
        System.out.println("Inside subProject deleting method");
        try {
            String url = dbUrl+"/db/api/subProject/delete/"+subProjectId;
            RequestEntity request = RequestEntity.get(url).build();
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.DELETE,request, Object.class);
            System.out.println("Deleted successfully");
        }catch (Exception e){
            System.out.println("failed Deleting subProject");
            throw new RuntimeException("failed Deleting subProject");
        }
    }

    public ResponseEntity updateSubProject(SubProjectReqDTO requestDTO){
        System.out.println("Inside subProject updating method");
        try {
            String url = dbUrl+"/db/api/subProject/update";
            RequestEntity request = RequestEntity.post(url).body(requestDTO);
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.PUT,request, Object.class);
            System.out.println("Updated successfully");
            return response;
        }catch (Exception e){
            System.out.println("failed Updating subProject");
            throw new RuntimeException("failed Updating subProject");
        }
    }

    public Object getSubProject(UUID projectId){
        System.out.println("Inside subProject fetching method");
        try {
            String url = dbUrl+"/db/api/subProject/get/"+projectId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("posted Deleted successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching subProject");
            throw new RuntimeException("failed Fetching subProject");
        }
    }

    public Object createTask(TaskRequestDTO request,String userEmail){
        System.out.println("Inside task creating method");
        try {
            String endpoint = dbUrl+"/db/api/task/create/"+userEmail;
            ResponseEntity response = makePostCall(request,endpoint,"",new HashMap<>());
            System.out.println("created task successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed creating task");
            throw new RuntimeException("failed creating task");
        }
    }

    public void deleteTask(UUID taskId){
        System.out.println("Inside subProject deleting method");
        try {
            String url = dbUrl+"/db/api/task/delete/"+taskId;
            RequestEntity request = RequestEntity.get(url).build();
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.DELETE,request, Object.class);
            System.out.println("Deleted successfully");
        }catch (Exception e){
            System.out.println("failed Deleting subProject");
            throw new RuntimeException("failed Deleting subProject");
        }
    }

    public ResponseEntity updateTask(TaskRequestDTO requestDTO, String loggedUsername){
        System.out.println("Inside Task updating method");
        try {
            String url = dbUrl+"/db/api/task/update/"+loggedUsername;
            RequestEntity request = RequestEntity.post(url).body(requestDTO);
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.PUT,request, String.class);
            System.out.println("Updated successfully");
            return response;
        }catch (Exception e){
            System.out.println("failed Updating task");
            throw new RuntimeException("failed Updating task");
        }
    }

    public Object getTask(UUID subProjectId){
        System.out.println("Inside task fetching method");
        try {
            String url = dbUrl+"/db/api/task/get/"+subProjectId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("task fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching task");
            throw new RuntimeException("failed Fetching task");
        }
    }

    public Object createRole(RoleRequestDTO requestDTO,String userEmail) {
        System.out.println("Inside role creating method");
        try {
            String endpoint = dbUrl+"/db/api/role/create/"+requestDTO.getOrgId()+"/"+userEmail;
            ResponseEntity response = makePostCall(requestDTO,endpoint,"",new HashMap<>());
            System.out.println("created task successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed creating task");
            throw new RuntimeException("failed creating task");
        }
    }

    public Object getRoles(UUID orgId) {
        System.out.println("Inside role fetching method");
        try {
            String url = dbUrl+"/db/api/role/get/"+orgId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("roles fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching roles");
            throw new RuntimeException("failed Fetching roles");
        }
    }

    public void inviteMember(UUID orgId,String invitedMail,String role) {
        System.out.println("Inside inviting member method");
        try {
            String endpoint = dbUrl+"/db/api/user/create";
            HashMap<String,String> req = new HashMap<>();
            req.put("orgId",orgId+"");
            req.put("userEmail",invitedMail);
            req.put("userRole",role);
            String finalUrl = addQueryParams(endpoint,req);
            RequestEntity request = RequestEntity.post(finalUrl).body(null);
            ResponseEntity response = restTemplate.exchange(finalUrl,HttpMethod.POST,request, String.class);
            System.out.println("invited successfully");
        }catch (Exception e){
            System.out.println("failed adding role");
            throw new RuntimeException("failed adding role");
        }
    }

    public Object getMembers(UUID orgId) {
        System.out.println("Inside members fetching method");
        try {
            String url = dbUrl+"/db/api/org/users/"+orgId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("users fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching users");
            throw new RuntimeException("failed Fetching users");
        }
    }

    public Object addMemberRole(UUID subProjectId, UUID userId, UUID roleId) {
        System.out.println("Inside add role creating method");
        try {
            String endpoint = dbUrl+"/db/api/user/add/role/"+subProjectId+"/"+userId+"/"+roleId;
            ResponseEntity response = makePostCall(null,endpoint,"",new HashMap<>());
            System.out.println("roll added successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed adding role");
            throw new RuntimeException("failed adding role");
        }
    }

    public Object getSubProjectMembers(UUID subProjectId) {
        System.out.println("Inside members fetching method");
        try {
            String url = dbUrl+"/db/api/subProject/users/"+subProjectId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("users fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching users");
            throw new RuntimeException("failed Fetching users");
        }
    }

    public Object createStatus(UUID subProjectId, String statusName,String userEmail) {
        System.out.println("Inside status creating method");
        try {
            String endpoint = dbUrl+"/db/api/status/create/"+subProjectId+"/"+userEmail;
            StatusRequestDTO requestDTO = new StatusRequestDTO();
            requestDTO.setName(statusName);
            ResponseEntity response = makePostCall(requestDTO,endpoint,"",new HashMap<>());
            System.out.println("created status successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed creating status");
            throw new RuntimeException("failed creating status");
        }
    }

    public Object getStatus(UUID subProjectId) {
        System.out.println("Inside status fetching method");
        try {
            String url = dbUrl+"/db/api/status/get/"+subProjectId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("users fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching users");
            throw new RuntimeException("failed Fetching users");
        }
    }

    public void createComment(UUID task_id,CommentRequestDTO requestDTO) {
        System.out.println("Inside creating comment method");
        try {
            String endpoint = dbUrl+"/db/api/comment/create/"+task_id;
            RequestEntity request = RequestEntity.post(endpoint).body(requestDTO);
            ResponseEntity response = restTemplate.exchange(endpoint,HttpMethod.POST,request, String.class);
            System.out.println("comment created successfully");
        }catch (Exception e){
            System.out.println("failed adding comment");
            throw new RuntimeException("failed adding comment");
        }
    }

    public Object getComments(UUID taskId) {
        System.out.println("Inside comments fetching method");
        try {
            String url = dbUrl+"/db/api/comment/get/"+taskId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("comments fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching comments");
            throw new RuntimeException("failed Fetching comments");
        }
    }

    public Object getOperations() {
        System.out.println("Inside operations fetching method");
        try {
            String url = dbUrl+"/db/api/operation/get";
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("comments fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching comments");
            throw new RuntimeException("failed Fetching comments");
        }
    }

    public Object getGithubRepos(String loggedEmail) {
        System.out.println("Inside github repos fetching method");
        try {
            String url = dbUrl+"/api/github/get/repos/"+loggedEmail;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("repos fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching repo");
            throw new RuntimeException("failed Fetching repo");
        }
    }

    public Object getGithubPr(String ticketId) {
        System.out.println("Inside github pr fetching method");
        try {
            String url = dbUrl+"/api/github/get/pr/"+ticketId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("pr fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching pr");
            throw new RuntimeException("failed Fetching pr");
        }
    }


    public Object getHistory(String ticketId) {
        System.out.println("Inside history fetching method");
        try {
            String url = dbUrl+"/db/api/task/history/"+ticketId;
            ResponseEntity response = makeGetCall(url,"",new HashMap<>());
            System.out.println("history fetch successfully");
            return response.getBody();
        }catch (Exception e){
            System.out.println("failed Fetching history");
            throw new RuntimeException("failed Fetching history");
        }
    }
}
