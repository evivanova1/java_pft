package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {


  public Executor getExecutor() {
    return Executor.newInstance().auth("601f5dd9c548847641dc26728bc24eab", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> getIssues = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue bug = getIssues.iterator().next();
    if (bug.getState_name().equals("Решен") || bug.getState_name().equals("Закрыт")) {
      return false;
    }
    return true;
  }
}
