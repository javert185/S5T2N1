package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadors")
public class Jugador { 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "data_registre", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDeRegistre;
    
    public Jugador() {
    	
    }

    public Jugador(int id, String nom, Date dataDeRegistre) {
        this.id = id;
        this.nom = nom;
        this.dataDeRegistre = dataDeRegistre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDataDeRegistre() {
        return dataDeRegistre;
    }

    public void setDataDeRegistre(Date dataDeRegistre) {
        this.dataDeRegistre = dataDeRegistre;
    }

    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nom=" + nom + ", dataDeRegistre=" + dataDeRegistre + "]";
    }
}