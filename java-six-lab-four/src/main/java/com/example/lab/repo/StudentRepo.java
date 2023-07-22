package com.example.lab.repo;

import com.example.lab.model.Student;
import com.example.lab.utils.StudentMap;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class StudentRepo {

    RestTemplate rest = new RestTemplate();

    String url = "https://example-firebase-one-default-rtdb.firebaseio.com/students.json";

    private String getUrl(String key) {
        return url.replace(".json", "/" + key + ".json");
    }

    public StudentMap getAll() {
        return rest.getForObject(url, StudentMap.class);
    }

    public Student findOneById(String key) {
        return rest.getForObject(getUrl(key), Student.class);
    }

    public String create(Student data) {
        HttpEntity<Student> entity = new HttpEntity<>(data);
        JsonNode resp = rest.postForObject(url, entity, JsonNode.class);
        assert resp != null;
        return resp.get("name").asText();
    }

    public Student update(String key, Student data) {
        HttpEntity<Student> entity = new HttpEntity<>(data);
        rest.put(getUrl(key), entity);
        return data;
    }

    public void delete(String key) {
        rest.delete(getUrl(key));
    }
}
