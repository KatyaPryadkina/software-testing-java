package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {
    @BeforeClass
    public void init(){
       // RestAssured.authentication = RestAssured.basic("7172fcb5f1888f5fac3dced24caeaa6a", "");  //неверная библиотека implementation("com.jayway.restassured:rest-assured-parent:2.9.0") и не ищет auth
        io.restassured.RestAssured.authentication = io.restassured.RestAssured.basic("7172fcb5f1888f5fac3dced24caeaa6a", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New Test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);


    }


    private Set<Issue> getIssues() throws IOException {            //способ работы с http во fluent-стиле

        String json = io.restassured.RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=100").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issue");//ищем (парсим) в теле json- issue

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());                          //преобразовываем в нужный вид
    }



    private int createIssue(Issue newIssue) throws IOException {

        String json = io.restassured.RestAssured.given().parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
