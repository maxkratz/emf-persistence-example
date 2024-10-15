# EMF Persistence Example

This example shows how to persist and load EMF models as XMI files with the standard EMF resource implementation and with the SmartEMF resource implementation.

[SmartEMF](https://github.com/eMoflon/emoflon-core/tree/master/org.emoflon.smartemf) is a custom implementation conforming to the EMF interfaces developed by the [eMoflon::IBeX team](https://emoflon.org/dev-team/).


## Structure

| Name                                                         | Type                          | Purpose                                                             |
| ------------------------------------------------------------ | ----------------------------- | ------------------------------------------------------------------- |
| [examplemetamodel](examplemetamodel)                         | EMF metamodeling project      | Contains the Ecore and the Genmodel files to generate code from.    |
| [example.model.tests](example.model.tests)                   | Java project with JUnit tests | Contains JUnit tests for testing the EMF/SmartEMF implementation.   |
| [example.model.utils](example.model.utils)                   | EMF persistence utils         | Writing and reading XMI files with the standard EMF implementation. |
| [example.model.utils.smartemf](example.model.utils.smartemf) | SmartEMF persistence utils    | Writing and reading XMI files with the SmartEMF implementation.     |


## How to build and run

Requirements:
- You need an up-to-date Eclipse Modeling IDE. Ideally, you use the [eMoflon::IBeX Eclipse application](https://emoflon.org/download/#emoflonibex) because it has all necessary SmartEMF and EMF features pre-installed.
- Java 21 JDK installed and ready to use.

Steps:
- Checkout this repository and import all projects in Eclipse.
- Build all projects.
- Right click on the project `example.model.tests` -> `Run As` -> `JUnit test`


## License

This project is licensed under the Eclipse Public License - v1.0 - see the [LICENSE](LICENSE) file for more details.
