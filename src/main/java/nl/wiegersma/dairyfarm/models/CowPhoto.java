package nl.wiegersma.dairyfarm.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CowPhotos")
public class CowPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String url;
    private String contentType;
    @Lob
    private byte[] contents;

    @OneToOne
    @JoinColumn(name = "cow_id")
    private Cow cow;

    public CowPhoto(){}

    public CowPhoto(String title, String url, String contentType, byte[] contents, Cow cow) {
        this.title = title;
        this.url = url;
        this.contentType = contentType;
        this.contents = contents;
        this.cow = cow;
    }


}

