class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val adapter = EventAdapter(EventStore.getAll())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }
}
