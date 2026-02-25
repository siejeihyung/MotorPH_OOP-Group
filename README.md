<h1>MotorPH Payroll System</h1>

<h3>Project Overview</h3>
<ul>
<li><strong>Purpose:</strong> A Java-based payroll system for MotorPH to streamline employee management and automate salary computations.</li>
<li><strong>Tech Stack:</strong> Built using <strong>Java Swing</strong> for the GUI and <strong>CSV/Text files</strong> for data storage (managed via OpenCSV).</li>
<li><strong>Core Functions:</strong> Automated calculations for SSS, PhilHealth, Pag-IBIG, and Withholding Tax.</li>
<li><strong>Development Note:</strong> This system has been successfully transitioned to a professional <strong>Object-Oriented Programming (OOP)</strong> architecture.</li>
</ul>

<h3>System Access & User Credentials</h3>
<p>The system utilizes a <code>FileHandler</code> to authenticate users and direct them to their specific dashboards:</p>

<h4>1. Administrator</h4>
<ul>
<li><strong>Username:</strong> admin</li>
<li><strong>Password:</strong> 1234</li>
<li><strong>Features:</strong> Full access to the Admin Dashboard (CRUD). Can check all employee records and print payslips for any employee.</li>
</ul>

<h4>2. Regular Employee</h4>
<ul>
<li><strong>Username:</strong> [Employee ID] (e.g., 10001)</li>
<li><strong>Password:</strong> [Last Name] (e.g., Garcia)</li>
<li><strong>Features:</strong> Access to personal information and the ability to view/print their individual payslips.</li>
</ul>

<h3>Core OOP Principles Implementation</h3>

<h4>1. Encapsulation (Data Integrity)</h4>
<p><strong>Definition:</strong> Bundling data (fields) and the methods that operate on them into a single unit while restricting direct access to the internal state.</p>

<ul>
<li><strong>Purpose & Help:</strong> It ensures <strong>Data Integrity</strong>. By making fields private, you prevent external classes from setting invalid values (like a negative salary). It also makes the code easier to maintain because you can change internal logic without breaking other parts of the system.</li>
<li><strong>Where to find it:</strong>
<ul>
<li><code>Benefits.java</code>: All benefit fields (rice, phone, clothing) are <strong>private</strong>. They can only be accessed through Public Getters and Setters.</li>
<li><code>Employee.java</code>: Fields like <code>employeeID</code> and <code>basicSalary</code> are <strong>protected</strong>, allowing child classes access while hiding them from the rest of the application.</li>
</ul>
</li>
</ul>

<h4>2. Abstraction (Reducing Complexity)</h4>
<p><strong>Definition:</strong> Hiding complex implementation details and showing only the essential features (the "what" instead of the "how").</p>

<ul>
<li><strong>Purpose & Help:</strong> It <strong>Reduces Complexity</strong>. The user of a class (like the GUI) doesn't need to know the complex SSS contribution formulas; they just need to call a simple method name.</li>
<li><strong>Where to find it:</strong>
<ul>
<li><code>IPayable.java</code> (Interface): Defines a "contract" stating that anything "Payable" must have a <code>calculateGrossPay()</code> method, without defining the math.</li>
<li><code>Employee.java</code> (Abstract Class): Marked as <code>public abstract class</code>. It serves as a blueprint for specific employee types and cannot be instantiated directly.</li>
<li><code>Deductions.java</code>: Hides complicated tax and contribution matrices behind simple methods like <code>calculateSSS()</code>.</li>
</ul>
</li>
</ul>

<h4>3. Inheritance (Code Reusability)</h4>
<p><strong>Definition:</strong> A mechanism where one class (subclass) acquires the properties and behaviors of another (superclass).</p>
<ul>
<li><strong>Purpose & Help:</strong> It promotes <strong>Code Reusability</strong>. You don't have to rewrite the "First Name," "Last Name," and "ID" logic for every employee type; you write it once in the parent and reuse it.</li>
<li><strong>Where to find it:</strong>
<ul>
<li><code>RegularEmployee.java</code>: Uses the <code>extends</code> keyword (<code>public class RegularEmployee extends Employee</code>).</li>
<li><strong>super() call:</strong> Inside the <code>RegularEmployee</code> constructor, it calls <code>super(...)</code> to pass data up to the <code>Employee</code> parent class for initialization.</li>
</ul>
</li>
</ul>

<h4>4. Polymorphism (Flexibility)</h4>
<p><strong>Definition:</strong> Allowing a subclass to provide a specific implementation of a method defined in its parent.</p>
<ul>
<li><strong>Purpose & Help:</strong> It provides <strong>Flexibility and Scalability</strong>. You can have a list of <code>Employee</code> objects, and Java automatically knows whether to use the logic for a <code>RegularEmployee</code> or other types at runtime.</li>
<li><strong>Where to find it:</strong>
<ul>
<li><code>RegularEmployee.java</code>: Uses the <code>@Override</code> annotation on <code>calculateGrossPay()</code>. This "redefines" the abstract method from the parent specifically for regular staff.</li>
</ul>
</li>
</ul>

<h4>5. Composition (Modular Design)</h4>
<p><strong>Definition:</strong> A design technique where a class is composed of one or more objects of other classes (a "Has-A" relationship).</p>
<ul>
<li><strong>Purpose & Help:</strong> It creates <strong>Modular Code</strong>. Instead of the <code>Employee</code> class being cluttered with 20 variables, it "has" a <code>Benefits</code> object.</li>
<li><strong>Where to find it:</strong>
<ul>
<li><code>Employee.java</code>: The field <code>protected double totalBenefits</code> (or <code>Benefits benefits</code>) represents composition.</li>
<li><code>PayrollLogic.java</code>: Contains a <code>private Deductions deductions</code> field to perform its work.</li>
</ul>
</li>
</ul>

<h3>OOP Implementation Summary Table</h3>
<table border="1" style="width:100%; border-collapse: collapse; text-align: left;">
<tr style="background-color: #f2f2f2;">
<th>Principle</th>
<th>File(s) Involved</th>
<th>Specific Implementation</th>
</tr>
<tr>
<td><strong>Encapsulation</strong></td>
<td>Benefits.java, Employee.java</td>
<td>Private fields, public Getters/Setters.</td>
</tr>
<tr>
<td><strong>Abstraction</strong></td>
<td>IPayable.java, Employee.java</td>
<td>Use of interface and abstract method signatures.</td>
</tr>
<tr>
<td><strong>Inheritance</strong></td>
<td>RegularEmployee.java</td>
<td><code>extends Employee</code> and use of <code>super()</code>.</td>
</tr>
<tr>
<td><strong>Polymorphism</strong></td>
<td>RegularEmployee.java</td>
<td>Method Overriding (<code>@Override</code>) of <code>calculateGrossPay()</code>.</td>
</tr>
<tr>
<td><strong>Composition</strong></td>
<td>PayrollLogic.java, Employee.java</td>
<td>Objects as fields (e.g., <code>Deductions</code> inside <code>PayrollLogic</code>).</td>
</tr>
</table>

<h3>Professional Architecture (MVC)</h3>
<ul>
<li><strong>Model:</strong> <code>Employee.java</code>, <code>Benefits.java</code>, <code>Attendance.java</code> (Data & Objects)</li>
<li><strong>View:</strong> Java Swing UI components (User Interface)</li>
<li><strong>Controller:</strong> <code>PayrollLogic.java</code>, <code>FileHandler.java</code> (Logic & Data Flow)</li>
</ul>

<h3>Development Tracking</h3>
<ul>
<li><strong>Sunny Eljohn Lico:</strong>
<ul>
<li>Reworked the Login system and Dashboard redirection for specific user types.</li>
<li>Resolved calculation bugs and updated the <strong>Payslip Printing feature</strong>.</li>
<li><strong>Lead Architect:</strong> Successfully transitioned the procedural codebase into a professional OOP architecture.</li>
</ul>
</li>
</ul>

<h3>How to Run</h3>
<ul>
<li>Open the project in a Java IDE (e.g., NetBeans, IntelliJ, or Eclipse).</li>
<li>Ensure <code>employee.txt</code> and <code>attendance.txt</code> are located in the <code>data</code> folder.</li>
<li>Run the application and use the credentials provided in the System Access section.</li>
</ul>

<h3>UML Class Diagram (Visual Map)</h3>

<pre style="background-color: #f4f4f4; padding: 15px; border-left: 5px solid #2980b9;">
«Interface»
IPayable
(calculateGrossPay)
^
|
«Abstract»
Employee  <>------> Benefits (Composition: Employee HAS-A Benefits)
(Protected fields)   (Private fields)
^
|
RegularEmployee (Inheritance: RegularEmployee IS-AN Employee)
(@Override calculateGrossPay)
</pre>

