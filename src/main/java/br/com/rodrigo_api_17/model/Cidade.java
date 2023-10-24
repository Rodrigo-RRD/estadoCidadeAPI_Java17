package br.com.rodrigo_api_17.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;



@Data/* subtitui os get e set  */
//@NoArgsConstructor /* por default ele vem no codigo */
//@AllArgsConstructor
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    Estado estado;

}
