class DownloadTask extends Thread {
    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
        System.out.println(fileName + " → State: NEW");
    }

    @Override
    public void run() {
        System.out.println(fileName + " → State: RUNNABLE");

        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(fileName + " downloading... " + (i * 20) + "%");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(fileName + " interrupted.");
        }

        System.out.println(fileName + " → State: TERMINATED");
    }
}

public 5class DownloadManager {
    public static void main(String[] args) throws InterruptedException {

        DownloadTask file1 = new DownloadTask("File1.mp4");
        DownloadTask file2 = new DownloadTask("File2.zip");
        DownloadTask file3 = new DownloadTask("File3.pdf");

        file1.setPriority(Thread.MIN_PRIORITY);
        file2.setPriority(Thread.NORM_PRIORITY);
        file3.setPriority(Thread.MAX_PRIORITY);

        file1.start();
        file2.start();
        file3.start();

        System.out.println("\nChecking thread status using isAlive():");
        System.out.println("File1 alive: " + file1.isAlive());
        System.out.println("File2 alive: " + file2.isAlive());
        System.out.println("File3 alive: " + file3.isAlive());

        file1.join();
        file2.join();
        file3.join();

        System.out.println("\nAll downloads completed successfully!");
    }
}