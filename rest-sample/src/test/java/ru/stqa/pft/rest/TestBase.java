package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonObject parsedObject = parsed.getAsJsonObject();
        String status = parsedObject.get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
        if (status.equals("Resolved")) {
            return false;
        }
        return true;
    }

    org.apache.http.client.fluent.Executor getExecutor() {
        return Executor.newInstance().auth("7172fcb5f1888f5fac3dced24caeaa6a", "");
    }
}
