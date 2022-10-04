package com.sismics.docs.rest;

import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import com.sismics.docs.core.util.DirectoryUtil;
import com.sismics.util.filter.TokenBasedSecurityFilter;
import com.sismics.util.mime.MimeType;
import com.sismics.util.mime.MimeTypeUtil;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

/**
 * Exhaustive test of the file resource.
 * 
 * @author bgamard
 */
public class TestFileRatingResource extends BaseJerseyTest {
    /**
     * Test the file resource.
     * 
     * @throws Exception e
     */
    @Test
    public void testFileResource() throws Exception {
        // Login file_resources
        clientUtil.createUser("file_resources");
        String file1Token = clientUtil.login("file_resources");
        
        // Create a document
        String document1Id = clientUtil.createDocument(file1Token);
        
        // Add a file
        String file1Id = clientUtil.addFileToDocument(FILE_PIA_00452_JPG, file1Token, document1Id);
        String file2Id = clientUtil.addFileToDocument(FILE_PIA_00452_JPG, file1Token, document1Id);

        // Create a new rating
        JsonObject json = target().path("/file/" + file1Id + "/rating").request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, file1Token)
                .put(Entity.form(new Form()
                    .param("academic", "10")
                    .param("activities", "9")
                    .param("experience", "8")
                    .param("awards", "7")
                    .param("overall", "6")
                    .param("comment", "test_comment")
                    ), JsonObject.class
                );
        
        Assert.assertEquals("ok", json.getString("status"));
        Assert.assertEquals("false", json.getString("rating_exists"));

        // Get all ratings from file1, should have 1
        json = target().path("/file/" + file1Id + "list")
                .request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, file1Token)
                .get(JsonObject.class);
        JsonArray fileRatings = json.getJsonArray("file_ratings");
        Assert.assertEquals(1, fileRatings.size());
        Assert.assertEquals(10, fileRatings.getJsonObject(0).getInt("academics"));
        Assert.assertEquals(9, fileRatings.getJsonObject(0).getInt("activities"));
        Assert.assertEquals(8, fileRatings.getJsonObject(0).getInt("experience"));
        Assert.assertEquals(7, fileRatings.getJsonObject(0).getInt("awards"));
        Assert.assertEquals(6, fileRatings.getJsonObject(0).getInt("overall"));
        Assert.assertEquals("test_comment", fileRatings.getJsonObject(0).getString("comment"));

        // try to create another rating, should say already exists.
        json = target().path("/file/" + file1Id + "/rating").request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, file1Token)
                .put(Entity.form(new Form()
                    .param("academic", "1")
                    .param("activities", "2")
                    .param("experience", "3")
                    .param("awards", "4")
                    .param("overall", "5")
                    .param("comment", "test_comment_not")
                    ), JsonObject.class
                );
        
        Assert.assertEquals("ok", json.getString("status"));
        Assert.assertEquals("true", json.getString("rating_exists"));

        // Get all ratings from file1, should be same as before
        json = target().path("/file/" + file1Id + "list")
                .request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, file1Token)
                .get(JsonObject.class);
        fileRatings = json.getJsonArray("file_ratings");
        Assert.assertEquals(1, fileRatings.size());
        Assert.assertEquals(10, fileRatings.getJsonObject(0).getInt("academics"));
        Assert.assertEquals(9, fileRatings.getJsonObject(0).getInt("activities"));
        Assert.assertEquals(8, fileRatings.getJsonObject(0).getInt("experience"));
        Assert.assertEquals(7, fileRatings.getJsonObject(0).getInt("awards"));
        Assert.assertEquals(6, fileRatings.getJsonObject(0).getInt("overall"));
        Assert.assertEquals("test_comment", fileRatings.getJsonObject(0).getString("comment"));

        // Get all ratings from File2, should be empty list
        json = target().path("/file/" + file2Id + "list")
        .request()
        .cookie(TokenBasedSecurityFilter.COOKIE_NAME, file1Token)
        .get(JsonObject.class);
        fileRatings = json.getJsonArray("file_ratings");
        Assert.assertEquals(1, fileRatings.size());

        // Deletes a file
        json = target().path("/file/" + file1Id).request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, file1Token)
                .delete(JsonObject.class);
        Assert.assertEquals("ok", json.getString("status"));

    }
}