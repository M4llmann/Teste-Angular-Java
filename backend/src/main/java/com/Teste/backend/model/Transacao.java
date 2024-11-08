

@Entity

@Table(name = "Transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransacao")
    private Integer idTransacao;

    @ManyToOne
    @JoinColumn(name = "idConta", referencedColumnName = "idConta", nullable = false)
    private Conta conta;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "dataTransacao", nullable = false)
    private LocalDateTime dataTransacao;

}

