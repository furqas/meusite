package meusite.repository.post.jpa;

import jakarta.persistence.*;
import meusite.controller.post.dto.FeedPostDtoResponse;
import meusite.domain.User.User;
import meusite.domain.post.Post;
import meusite.repository.post.exception.PostException;
import meusite.repository.user.jpa.UserJpaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="posts")
public class PostJpaEntity{

    @Column(name="post_content")
    private String content;

    @ManyToOne
    @JoinColumn(name="id")
    private UserJpaEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tweet_id")
    private long tweetId;

    @Column(name="posted_at")
    private Instant timeStampTweet;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "ncoments")
    private Integer ncoments;


    public UserJpaEntity getUser(){
        return this.user;
    }

    public String getContent() {
    return content;
}
    public PostJpaEntity(){

    }


    public Instant getTimeStampTweet(){
        return this.timeStampTweet;
    }

    public PostJpaEntity(String content, UserJpaEntity user,Instant timeStampTweet){
        this.content=  content;
        this.user = user;
        this.timeStampTweet = timeStampTweet;

    }

    public PostJpaEntity(String content, UserJpaEntity user, long tweetId, Instant timeStampTweet, Integer likes, Integer nComents) {
        this.content = content;
        this.user = user;
        this.tweetId = tweetId;
        this.timeStampTweet = timeStampTweet;
        this.likes = likes;
        this.ncoments = nComents;
    }

    public void setContent(String content){
        this.content = content;
    }

    private PostJpaEntity(String content, UserJpaEntity user, long tweetId, Instant timeStampTweet) {
        this.content = content;
        this.user = user;
        this.tweetId = tweetId;
        this.timeStampTweet = timeStampTweet;
    }

    public static PostJpaEntity from(Post post){
        return new PostJpaEntity(
                post.getContent(),
                post.getUser(),
                post.getTweetId(),
                post.getTimeStampTweet(),
                post.getLikes(),
                post.getComents()
        );
    }
    public static FeedPostDtoResponse toModel(PostJpaEntity postJpaEntity){
        String originalDateTime = postJpaEntity.getTimeStampTweet().toString();

        // Converter a string original para Instant
        Instant instant = Instant.parse(originalDateTime);

        // Converter Instant para ZonedDateTime (opcional: ajustar o fuso horário)
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));

        // Formatar ZonedDateTime para a string desejada
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = formatter.format(zonedDateTime);


        return new FeedPostDtoResponse(
                postJpaEntity.getContent(),
                postJpaEntity.getUser().getEmail(),
                formattedDateTime,postJpaEntity.getTweetId(), postJpaEntity.getLikes(), postJpaEntity.getComents());
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

    public long getTweetId() {
        return this.tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public void setTimeStampTweet(Instant timeStampTweet) {
        this.timeStampTweet = timeStampTweet;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComents() {
        return ncoments;
    }

    public void setComents(Integer coments) {
        this.ncoments = coments;
    }
}
