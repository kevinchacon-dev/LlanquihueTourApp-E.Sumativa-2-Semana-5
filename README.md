🧠 Evaluación Final Transversal – Desarrollo Orientado a Objetos I
👤 Autor del proyecto

    Nombre completo: Kevin Chacón Flores


    Carrera: Analista Programador
    

📘 Descripción general del sistema

Este proyecto corresponde a la Evaluación Final Transversal de la asignatura Desarrollo Orientado a Objetos I. Consiste en el desarrollo de un sistema de gestión turística denominado Llanquihue Tour, implementado en Java.

El objetivo del sistema es administrar información de distintos tours turísticos de la zona del Lago Llanquihue, permitiendo cargar, almacenar, consultar y gestionar sus datos.

La aplicación obtiene la información desde un archivo Excel (tours.xlsx), utilizando la biblioteca Apache POI para su lectura y procesamiento.

Durante el desarrollo se aplicaron conceptos de programación orientada a objetos, incluyendo encapsulamiento y composición, junto con estructuras de datos y validaciones para mantener la integridad de la información.

Entre sus principales funcionalidades se encuentran:

    Carga de información turística desde archivos Excel.

    Validación de datos y detección de registros duplicados.

    Almacenamiento de tours mediante colecciones.

    Búsqueda de tours por nombre.

    Registro y administración del historial de consultas.

    Manejo de errores durante la lectura de los datos.

🧱 Estructura general del proyecto

LlanquihueTourApp/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── data/
│       │   │   └── GestorDatos.java
│       │   │
│       │   ├── model/
│       │   │   ├── Tour.java
│       │   │   └── AgenciaTuristica.java
│       │   │
│       │   └── ui/
│       │       └── Main.java
│       │
│       └── resources/
│           └── tours.xlsx
│
├── target/
│   └── LlanquihueTourApp-1.0-SNAPSHOT.jar
│
├── pom.xml
└── README.md

Tecnologías y herramientas utilizadas

    Java 25.

    IntelliJ IDEA.

    Apache Maven.

    Apache POI 5.4.1 para lectura de archivos Excel.

    ArrayList para almacenar los tours.

    HashMap para realizar búsquedas por nombre.

    Stack para administrar el historial de consultas.

⚙️ Instrucciones para clonar y ejecutar el proyecto

    Clona el repositorio desde GitHub:

git clone [URL_DEL_REPOSITORIO]

    Abre el proyecto en IntelliJ IDEA.

    Verifica que el archivo tours.xlsx se encuentre en la siguiente ubicación:

src/main/resources/tours.xlsx

    Espera a que Maven descargue y configure las dependencias del proyecto.

    Para ejecutar el programa desde IntelliJ IDEA, abre la clase Main.java, ubicada en el paquete ui, y ejecuta su método main().

Generación del archivo JAR

Para generar el archivo ejecutable con sus dependencias, utiliza la terminal de Maven y ejecuta:

mvn clean package

El archivo JAR se generará en la carpeta target con el siguiente nombre:

LlanquihueTourApp-1.0-SNAPSHOT.jar

Para ejecutar el JAR desde la carpeta raíz del proyecto:

java -jar target/LlanquihueTourApp-1.0-SNAPSHOT.jar

Requisito: tener instalado un JDK compatible con Java 25.

Repositorio GitHub: [Pega aquí el enlace al repositorio]

Archivo ejecutable: LlanquihueTourApp-1.0-SNAPSHOT.jar

Fecha de entrega: [DD/MM/2026]

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT