# RestAPITestings
Great! Since your GitHub project is focused on **REST API Validations using Rest Assured**, your `README.md` should clearly explain what your project does, how to run it, and what tools/libraries you're using.

Here's a professional and informative template you can use or adapt:

---

## 🧪 REST API Validations with Rest Assured

This project demonstrates how to perform automated testing and validation of RESTful APIs using **Rest Assured**, **TestNG**, and **JSON Schema Validation**.

---

### 🚀 Features

* REST API testing using **Rest Assured**
* Response body, headers, and status code validation
* JSON Schema validation
* Response time assertions
* Test automation with **TestNG**
* Log and debug API responses

---

### 🛠️ Tools & Technologies

* Java 17+
* [Rest Assured](https://rest-assured.io/)
* TestNG
* JSON Schema Validator (via Rest Assured module)
* Maven (or Gradle, depending on your build system)

---

### 📂 Project Structure

```
src/
├── test/
│   ├── java/
│   │   └── Restassured/
│   │       └── restapitestings/
│   │           └── ReqresGetRequestTesting.java
│   └── resources/
│       └── userschema.json
```

---

### 📦 Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```

2. **Import as a Maven/Gradle project in IntelliJ IDEA or Eclipse.**

3. **Run the tests:**

   ```bash
   mvn test
   ```

---

### 🔍 Test Description

* **API Under Test:** [https://reqres.in/api/users?page=2](https://reqres.in/api/users?page=2)
* **Validations include:**

  * Status code and status line
  * Response headers
  * Response body field values and data types
  * JSON Schema matching
  * API response time

---

### 📄 Sample JSON Schema

Located in `src/test/resources/userschema.json`. This file defines the expected structure of the API response and is used for schema validation.

---

### 📸 Sample Test Output

* `Status Code: 200`
* `Response Time: <2000 ms`
* `Schema Valid: ✅`

---

### 🤝 Contribution

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

### 📜 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

Would you like me to generate a JSON schema file (`userschema.json`) based on the actual API response from reqres.in?
