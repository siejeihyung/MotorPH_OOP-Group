<h1>MOTORPH PAYROLL SYSTEM (CORE MODEL)</h1>

<p>A Java-based payroll management system designed with Object-Oriented Programming (OOP) principles to handle employee records, attendance, and salary computations.</p>

<hr>

<h3>🚀 KEY FEATURES & OOP IMPLEMENTATION</h3>

<ul>
<li>
<b>1. ABSTRACTION (The "What")</b>
<ul>
<li><b>IPayable Interface:</b> Defines the mandatory 'calculateGrossPay()' contract.</li>
<li><b>Employee Abstract Class:</b> Holds shared data (ID, Name, Salary) but leaves the actual gross pay calculation to specific subclasses.</li>
</ul>
</li>
<li>
<b>2. POLYMORPHISM (The "Flexibility")</b>
<ul>
<li><b>PayrollLogic.processNetPay(Employee emp):</b> This method accepts any Employee type. It calls 'calculateGrossPay()', and Java automatically triggers the correct math at runtime.</li>
</ul>
</li>
<li>
<b>3. ENCAPSULATION (The "Security")</b>
<ul>
<li><b>Private Fields:</b> All data in Employee.java and Benefits.java is private to protect data integrity.</li>
<li><b>Getters & Setters:</b> Data access is controlled via public methods, allowing for validation (e.g., preventing negative salaries).</li>
</ul>
</li>
</ul>

<hr>

<h1>📂 MOTORPH PROJECT STRUCTURE</h1>

<h3>📦 model (Package)</h3>
<p>This folder contains the core logic and blueprints of your system.</p>
<ul>
<li>
<b>IPayable.java</b>
<ul>
<li>Type: Interface</li>
<li>Role: Defines the contract for any object that can be paid.</li>
</ul>
</li>
<li>
<b>Employee.java</b>
<ul>
<li>Type: Abstract Class</li>
<li>Role: The parent template for all employees; implements IPayable.</li>
</ul>
</li>
<li>
<b>RegularEmployee.java</b>
<ul>
<li>Type: Concrete Class</li>
<li>Role: Extends Employee; contains the specific math for full-time staff.</li>
</ul>
</li>
<li>
<b>PayrollLogic.java</b>
<ul>
<li>Type: Logic Class</li>
<li>Role: Processes net pay and prints salary breakdowns.</li>
</ul>
</li>
<li>
<b>Deductions.java</b>
<ul>
<li>Type: Calculation Class</li>
<li>Role: Computes SSS, PhilHealth, Pag-IBIG, and Tax.</li>
</ul>
</li>
<li>
<b>Benefits.java</b>
<ul>
<li>Type: Model Class</li>
<li>Role: Stores allowance data (Rice, Phone, Clothing).</li>
</ul>
</li>
<li>
<b>Attendance.java</b>
<ul>
<li>Type: Utility Class</li>
<li>Role: Manages login times and late deductions.</li>
</ul>
</li>
<li>
<b>FileHandler.java</b>
<ul>
<li>Type: Data Class</li>
<li>Role: Reads and writes to your text/CSV files.</li>
</ul>
</li>
</ul>

<h3>📂 data (Folder)</h3>
<p>This is where the system stores information outside of the code.</p>
<ul>
<li><b>employee.txt</b>: Stores the main employee database.</li>
<li><b>attendance.txt</b>: Stores daily time logs.</li>
<li><b>credentials.txt</b>: Stores login usernames and passwords.</li>
</ul>

<hr>

<h3>✅ SUMMARY OF UPDATES</h3>
<ul>
<li><b>Abstraction:</b> Method names are now synchronized to <b>calculateGrossPay()</b>.</li>
<li><b>Encapsulation:</b> All fields in Employee.java are now <b>private</b> to protect data.</li>
<li><b>Polymorphism:</b> PayrollLogic now accepts an <b>Employee object</b> instead of long lists of numbers for better efficiency.</li>
</ul>
<ul>

<hr>

<p><i>Copyright © 2026 MotorPH Developer - Sunny Eljohn Lico</i></p>
