package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_imgprods"
    )
    @SequenceGenerator(
            name = "sequence_id_imgprods",
            sequenceName = "sequence_img_produto",
            allocationSize = 1
    )
    @Column(name = "img_product_id")
    private Long id;

    @URL
    private String url;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto(){
    }

    public ImagemProduto(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }

}
