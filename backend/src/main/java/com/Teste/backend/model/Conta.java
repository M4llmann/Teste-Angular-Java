
@Entity

@Table(name = "Conta")

public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConta")
    private Integer idConta;

    @Column(name = "nomeTitular", nullable = false)
    private String nomeTitular;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "dataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

}

