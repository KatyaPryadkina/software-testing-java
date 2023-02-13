package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {
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
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=100")).returnContent().asString();   //получить списко всех баг-репортов в формате json
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issue");//ищем (парсим) в теле json- issue

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());                          //преобразовываем в нужный вид
    }


    private Executor getExecutor() {
        return Executor.newInstance().auth("7172fcb5f1888f5fac3dced24caeaa6a", "");
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),                                         //передача параметров в запросе = bodyForm
                        new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
