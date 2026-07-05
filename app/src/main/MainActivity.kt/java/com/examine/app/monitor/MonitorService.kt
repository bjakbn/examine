class MonitorService : Service() {

    private var pkg: String? = null
    private var watcher: FileEventWatcher? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        pkg = intent?.getStringExtra("pkg")

        watcher = FileEventWatcher(this, pkg!!)
        watcher?.startWatching()

        return START_STICKY
    }

    override fun onDestroy() {
        watcher?.stopWatching()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
