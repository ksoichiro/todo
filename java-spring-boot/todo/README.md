# TODO with Spring Boot

## Database

Setup MariaDB with Vagrant.

```sh
$ vagrant up
```

Modify private network IP address `192.168.33.10` in Vagrantfile if necessary.

## Launch app

```sh
$ ./gradlew bootRun
```

Wait until `Started Application in **.*** seconds` is shown.
Then open `http://localhost:8080` in your browser.

You can log in to the app with `test`/`test`.

## Development

* IntelliJ IDEA CE 14
* JDK 7 or 8
