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

<h3>📂 PROJECT STRUCTURE</h3>

<ul>
<li><b>IPayable.java:</b> Interface for the payment contract.</li>
<li><b>Employee.java:</b> Abstract base class for shared attributes.</li>
<li><b>RegularEmployee.java:</b> Concrete class for full-time pay logic.</li>
<li><b>PayrollLogic.java:</b> The engine for net pay and breakdowns.</li>
<li><b>Deductions.java:</b> SSS, PhilHealth, Pag-IBIG, and Tax math.</li>
<li><b>Attendance.java:</b> Lateness and hourly deduction logic.</li>
<li><b>FileHandler.java:</b> CSV/Text data persistence management.</li>
</ul>

<hr>

<h3>📝 RECENT UPDATES</h3>

<ul>
<li><b>Standardized Naming:</b> Synchronized 'calculateGrossPay()' across all interfaces and classes.</li>
<li><b>Refactored Logic:</b> PayrollLogic now accepts <b>Employee objects</b> instead of multiple double parameters for cleaner code.</li>
<li><b>Enhanced Security:</b> Fields transitioned to <b>PRIVATE</b> with standard Getters/Setters.</li>
</ul>

<hr>

<p><i>Copyright © 2026 MotorPH Developer - Sunny Eljohn Lico</i></p>
