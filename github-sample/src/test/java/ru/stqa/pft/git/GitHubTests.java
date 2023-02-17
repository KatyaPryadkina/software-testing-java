package ru.stqa.pft.git;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

public class GitHubTests {
    @Test
    public void testCommits(){
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("KatyaPryadkina", "software-testing-java")).commits();
            for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {             //map-ассоциативный массив,карта  (встречаются часто)
                System.out.println(commit);
            }


    }
}
