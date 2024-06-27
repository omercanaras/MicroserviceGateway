<div id="documentation">
    <h2>Dokumentation</h2>
    <h3>1. Einführung</h3>
    <p><strong>Projektbeschreibung:</strong> MicroserviceGateway ist ein Projekt, das unter Verwendung einer Microservice-Architektur erstellt wurde. Dieses Projekt ermöglicht die Integration und Verwaltung verschiedener Microservices.</p>
    <p><strong>Ziel des Projekts:</strong> Das Ziel dieses Projekts ist es, die einfache Verwaltung und Weiterleitung von Microservices auf einer einzigen Plattform zu ermöglichen. Benutzer können auf verschiedene Microservices über ein einziges Gateway zugreifen und diese verwalten.</p>
    <p><strong>Umfang:</strong> Das Projekt umfasst grundlegende Funktionen wie die Integration, Verwaltung und Sicherheit von Microservices.</p>
    <h3>2. Installation und Ausführung</h3>
    <p><strong>Voraussetzungen:</strong></p>
    <ul>
        <li>Java Development Kit (JDK)</li>
        <li>Gradle</li>
        <li>Git</li>
        <li>Heroku CLI (Optional, für die Bereitstellung auf Heroku)</li>
    </ul>
    <p><strong>Installationsschritte:</strong></p>
    <ol>
        <li>Projekt klonen:
            <pre><code>git clone &lt;repository-url&gt;</code></pre>
        </li>
        <li>Zum Projektverzeichnis wechseln:
            <pre><code>cd MicroserviceGateway</code></pre>
        </li>
        <li>Erforderliche Abhängigkeiten herunterladen:
            <pre><code>./gradlew build</code></pre>
        </li>
    </ol>
    <p><strong>Startanweisungen:</strong></p>
    <ol>
        <li>Die Anwendung starten:
            <pre><code>./gradlew bootRun</code></pre>
        </li>
        <li>Nach dem Start ist die Anwendung standardmäßig unter <code>http://localhost:9090</code> erreichbar.</li>
    </ol>
    <h3>3. Architektur und Design</h3>
    <p><strong>Allgemeine Architektur:</strong> MicroserviceGateway ist ein Microservice-Gateway, das unter Verwendung von Spring Boot erstellt wurde. Es dient hauptsächlich der Integration und Verwaltung verschiedener Microservices. Das Projekt besteht aus folgenden Komponenten:</p>
    <ul>
        <li>API Gateway</li>
        <li>Authentication Service</li>
        <li>Routing Service</li>
    </ul>
    <p><strong>Verwendete Technologien:</strong></p>
    <ul>
        <li><strong>Spring Boot:</strong> Anwendungserstellung und -verwaltung.</li>
        <li><strong>Gradle:</strong> Projektverwaltung und -konfiguration.</li>
        <li><strong>Heroku:</strong> Anwendungsbereitstellung (optional).</li>
        <li><strong>Docker:</strong> Containerisierung und Bereitstellung.</li>
    </ul>
    <h3>4. Code-Struktur</h3>
    <p><strong>Datei- und Verzeichnisstruktur:</strong></p>
    <ul>
        <li><code>.git</code>: Git-Versionskontrollsystemdateien.</li>
        <li><code>.gradle</code>: Gradle-Konfigurationsdateien.</li>
        <li><code>.idea</code>: IntelliJ IDEA-Projektdateien.</li>
        <li><code>app.json</code>: Heroku-Anwendungskonfigurationsdatei.</li>
        <li><code>build</code>: Build-Ausgaben.</li>
        <li><code>build.gradle</code>: Gradle-Konfigurationsdatei.</li>
        <li><code>gradle</code>: Gradle Wrapper-Dateien.</li>
        <li><code>gradlew</code>: Gradle Wrapper für Unix-Systeme.</li>
        <li><code>gradlew.bat</code>: Gradle Wrapper für Windows-Systeme.</li>
        <li><code>Procfile</code>: Befehle zum Ausführen der Anwendung auf Heroku.</li>
        <li><code>src</code>: Projektquellcode.</li>
        <li><code>system.properties</code>: Systemkonfigurationsdatei.</li>
    </ul>
    <h3>5. API-Dokumentation</h3>
    <p><strong>Verwendete APIs:</strong> Die API-Dokumentation des Projekts kann automatisch mit einem Tool wie Swagger erstellt werden. Einige grundlegende API-Endpunkte sind unten aufgeführt:</p>
    <ul>
        <li><code>GET /api/services</code>: Listet vorhandene Dienste auf.</li>
        <li><code>POST /api/services</code>: Fügt einen neuen Dienst hinzu.</li>
        <li><code>PUT /api/services/{id}</code>: Aktualisiert den Dienst mit der angegebenen ID.</li>
        <li><code>DELETE /api/services/{id}</code>: Löscht den Dienst mit der angegebenen ID.</li>
    </ul>
    <h4>Retrofit-Verwendung:</h4>
    <p>Retrofit ist eine Bibliothek, die es ermöglicht, HTTP-Anfragen einfach und effizient zu stellen. Im Projekt werden API-Aufrufe mit Retrofit durchgeführt.</p>
    <h5>Schritt 1: Hinzufügen der Retrofit-Abhängigkeit</h5>
    <pre><code>// In der Datei build.gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}</code></pre>
    <h5>Schritt 2: Erstellen und Verwenden des Retrofit-Clients</h5>
    <pre><code>public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}</code></pre>
    <h5>Schritt 3: Verwendung der API</h5>
    <pre><code>public class MainActivity extends AppCompatActivity {
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getClient("http://localhost:9090");
        apiService = retrofit.create(ApiService.class);

        // Beispiel-API-Aufruf
        apiService.getServices().enqueue(new Callback&lt;List&lt;Service&gt;&gt;() {
            @Override
            public void onResponse(Call&lt;List&lt;Service&gt;&gt; call, Response&lt;List&lt;Service&gt;&gt; response) {
                if (response.isSuccessful()) {
                    List&lt;Service&gt; services = response.body();
                    // Verarbeitung der empfangenen Daten
                }
            }

            @Override
            public void onFailure(Call&lt;List&lt;Service&gt;&gt; call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}</code></pre>
    <h3>6. Tests</h3>
    <p><strong>Teststrategie:</strong> Das Projekt enthält Unit-Tests und Integrationstests. Die Tests wurden mit JUnit und Mockito geschrieben.</p>
    <p><strong>Testschritte:</strong></p>
    <ol>
        <li>Tests ausführen:
            <pre><code>./gradlew test</code></pre>
        </li>
    </ol>
    <h3>7. Fehlerbehebung und Debugging</h3>
    <p><strong>Häufige Probleme und Lösungen:</strong></p>
    <ul>
        <li><strong>Verbindungsfehler:</strong> Verbindungsprobleme sind normalerweise auf Konfigurationsfehler zurückzuführen. Überprüfen Sie die Datei <code>application.properties</code>.</li>
        <li><strong>Abhängigkeitskonflikte:</strong> Überprüfen Sie die Gradle-Konfiguration und führen Sie den Befehl <code>gradle clean</code> aus, um das Projekt zu bereinigen.</li>
    </ul>
    <p><strong>Debugging-Tipps:</strong></p>
    <ul>
        <li>Überprüfen Sie die Log-Dateien.</li>
        <li>Verwenden Sie Spring Boot Actuator, um Anwendungsmetriken und den Gesundheitszustand zu überwachen.</li>
    </ul>
    <h3>8. Mitwirken</h3>
    <p><strong>Beitragsregeln:</strong></p>
    <ul>
        <li>Forken Sie das Projekt und erstellen Sie einen neuen Branch.</li>
        <li>Nehmen Sie Ihre Änderungen vor und testen Sie diese.</li>
        <li>Erstellen Sie eine Pull-Request.</li>
    </ul>
    <p><strong>Entwicklungsprozess:</strong></p>
    <ol>
        <li>Projekt forken.</li>
        <li>Einen neuen Branch erstellen:
            <pre><code>git checkout -b feature/your-feature</code></pre>
        </li>
        <li>Änderungen vornehmen.</li>
        <li>Änderungen committen:
            <pre><code>git commit -m "Add your message"</code></pre>
        </li>
        <li>Branch pushen:
            <pre><code>git push origin feature/your-feature</code></pre>
        </li>
        <li>Eine Pull-Request erstellen.</li>
    </ol>
    <h3>9. Zusätzliche Informationen</h3>
    <p><strong>Häufig gestellte Fragen (FAQ):</strong></p>
    <ul>
        <li><strong>Wie starte ich die Anwendung?</strong> - Siehe Abschnitt "Startanweisungen".</li>
        <li><strong>Wie aktualisiere ich die Abhängigkeiten?</strong> - Führen Sie den Befehl <code>./gradlew build</code> aus.</li>
    </ul>
    <p><strong>Ressourcen und Links:</strong></p>
    <ul>
        <li><a href="https://spring.io/projects/spring-boot">Spring Boot Dokumentation</a></li>
        <li><a href="https://docs.gradle.org/current/userguide/userguide.html">Gradle Benutzerhandbuch</a></li>
        <li><a href="https://devcenter.heroku.com/articles/heroku-cli">Heroku CLI Dokumentation</a></li>
    </ul>
</div>
