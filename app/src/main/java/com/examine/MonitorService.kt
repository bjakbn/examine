class MonitorService : Service() {

    private var running = false
    private var targetPkg: String? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {

            "START" -> {
                targetPkg = intent.getStringExtra("pkg")
                running = true
                startMonitor()
            }

            "STOP" -> {
                running = false
            }
        }

        return START_STICKY
    }

    private fun startMonitor() {
        Thread {
            while (running) {
                scanFiles()
                Thread.sleep(2000)
            }
        }.start()
    }

    /**
     * ⚠️ Android限制：这里只能扫描公共目录/可访问目录
     */
    private fun scanFiles() {
        val dir = Environment.getExternalStorageDirectory()

        dir.listFiles()?.forEach {
            if (it.name.endsWith(".txt")) {

                EventStore.add(
                    Event(
                        type = "WRITE",
                        path = it.absolutePath,
                        time = System.currentTimeMillis(),
                        packageName = targetPkg ?: "unknown"
                    )
                )
            }
        }
    }

    override fun onBind(intent: Intent?) = null
}
