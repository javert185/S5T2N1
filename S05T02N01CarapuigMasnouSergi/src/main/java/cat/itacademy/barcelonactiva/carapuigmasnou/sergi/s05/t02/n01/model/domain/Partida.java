package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partides")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "jugador_id")
    private int jugadorId;

    @Column(name = "valor_dau1")
    private int valorDau1;

    @Column(name = "valor_dau2")
    private int valorDau2;

    @Column(name = "resultat")
    private int resultat;

    @Column(name = "guanyada")
    private boolean guanyada;

    public Partida() {
    	
    }
    public Partida(Integer jugadorId, int valorDau1, int valorDau2, int resultat, boolean guanyada) {
        this.jugadorId = jugadorId;
        this.valorDau1 = valorDau1;
        this.valorDau2 = valorDau2;
        this.resultat = resultat;
        this.guanyada = guanyada;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getJugadorId() {
        return jugadorId;
    }
    
    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }
    
    public int getValorDau1() {
        return valorDau1;
    }
    
    public void setValorDau1(int valorDau1) {
        this.valorDau1 = valorDau1;
    }
    
    public int getValorDau2() {
        return valorDau2;
    }
    
    public void setValorDau2(int valorDau2) {
        this.valorDau2 = valorDau2;
    }
    
    public int getResultat() {
        return resultat;
    }
    
    public void setResultat(int resultat) {
        this.resultat = resultat;
    }
    
    public boolean isGuanyada() {
        return guanyada;
    }
    
    public void setGuanyada(boolean guanyada) {
        this.guanyada = guanyada;
    }
}