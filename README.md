<h1 align="center">MotorPH Payroll & HR System — NewGUI Version 1.6</h1>
<p align="center">
<strong>Description:</strong> Enterprise-grade update featuring full Architectural Decoupling, DAO Pattern Implementation, and Advanced Object-Oriented Refactoring.
</p>

<hr>

<h2>📝 Description</h2>
<ul>
<li>Welcome to the official release of <b>MotorPH NewGUI (Version 1.6)</b>.</li>
<li>This milestone marks a significant shift from simple file management to a professional <b>Data Access Object (DAO)</b> architecture.</li>
<li>By eliminating "data leaks" and separating business logic from file I/O, Version 1.6 ensures the system is production-ready, scalable, and prepared for future database integration.</li>
</ul>

<h2>🚀 What's New in Version 1.6</h2>
<ul>
<li><b>Introduction of CredentialsDAO:</b> Structural decoupling is now complete. The <code>EmployeeService</code> no longer manually parses <code>credentials.csv</code>. All authentication data is handled by the <b>CredentialsDAO</b>, ensuring the "how" of data storage is hidden from the business layer.</li>
<li><b>Rich Object Modeling:</b> Moved away from "Raw Strings" and <code>String[]</code> arrays. The system now passes <b>Employee</b> and <b>RegularEmployee</b> objects throughout the application.
<ul>
<li><i>Benefit:</i> Developers can now call <code>emp.calculateGrossPay()</code> instead of managing fragile array indices, eliminating "Index Out of Bounds" errors.</li>
</ul>
</li>
<li><b>Plugging "Data Leaks":</b> In direct response to architectural feedback, <code>EmployeeService</code> has been "cleaned." It no longer contains references to <code>BufferedReader</code>, <code>FileReader</code>, or CSV delimiters. This <b>Separation of Concerns</b> ensures that a change in file format won't break the entire application.</li>
<li><b>Robust Authentication Flow:</b> <code>EmployeeService</code> now acts as a coordinator between two distinct data sources, checking <code>CredentialsDAO</code> for administrative roles and <code>EmployeeDAO</code> for regular employee identity.</li>
</ul>

<h2>🚀 What's New in Version 1.5 (Consolidated)</h2>
<ul>
<li><b>Unified IT Ticketing Engine:</b> Complete end-to-end lifecycle management for IT support, featuring a 6-column CSV persistence format and role-specific filtering.</li>
<li><b>Admin & IT Synchronization:</b> IT Support logins are now coordinated with Admin roles. The "Resolve" button and ticket icons have been fully polished and standardized.</li>
<li><b>UI/UX Polished Iconography:</b> High-resolution icons have been integrated across all dashboard buttons (Ticket, Leave, and Finance) for a premium enterprise look.</li>
</ul>

<h2>🛠 Technical Features (OOP Principles)</h2>
<p>Version 1.6 strengthens our implementation of OOP pillars to prevent data leakage and logic mixing:</p>

<ul>
<li><b>Encapsulation:</b> Data logic is strictly encapsulated within <b>DAO classes</b>. Business services can only access data through controlled method calls, protecting the integrity of the underlying CSV files.</li>
<li><b>Abstraction (DAO Pattern):</b> By using the Data Access Object pattern, we have created a boundary. The GUI and Service layers are now abstracted away from the file system, making it possible to switch to a MySQL database without rewriting the UI logic.</li>
<li><b>Inheritance:</b> <code>RegularEmployee</code> continues to extend the <code>Employee</code> base class, now utilizing the full power of object-passing rather than string-parsing.</li>
<li><b>Polymorphism:</b> Method overriding is used to handle specific authentication behaviors and calculation logic across the different specialized dashboards.</li>
</ul>

<h2>📋 System Roles & Access</h2>
<table border="1" style="width:100%; border-collapse: collapse; text-align: left;">
<tr style="background-color: #f2f2f2;">
<th>Role</th>
<th>Primary Access</th>
</tr>
<tr>
<td><b>ADMIN</b></td>
<td>Full system CRUD, global report viewing, and IT Ticket Status control.</td>
</tr>
<tr>
<td><b>IT SPECIALIST</b></td>
<td><b>Master Ticketing Management</b>, Troubleshooting Logs, and Admin-coordinated system oversight.</td>
</tr>
<tr>
<td><b>HR</b></td>
<td>Employee lifecycle management and leave auditing.</td>
</tr>
<tr>
<td><b>FINANCE</b></td>
<td>Salary computation, deduction auditing, and payslip generation.</td>
</tr>
<tr>
<td><b>EMPLOYEE</b></td>
<td>Profile management, Leave Filing, Attendance Clocking, and Ticket Submission/History.</td>
</tr>
</table>

<h2>⚠️ Current Known Issues & Roadmap</h2>
<ul>
<li><b>Notification System:</b> Developing real-time alerts for IT Specialists when a high-priority ticket is filed.</li>
<li><b>Export Feature:</b> Planning CSV/PDF export for Ticket History to assist in monthly performance audits.</li>
<li><b>Attachments:</b> Investigating support for screenshot/file attachments within the ticketing module.</li>
</ul>

<h2>⚙️ Development Setup</h2>
<ul>
<li><b>Clone Branch:</b> <code>git clone -b NewGUI_Version_1.6 https://github.com/siejeihyung/MotorPH_OOP-Group-28.git</code></li>
<li><b>CSV Integrity:</b> Ensure all data files are located in <code>src/data/</code>. DAOs are now configured to detect these paths automatically.</li>
<li><b>Build:</b> Perform a <b>"Clean and Build"</b> to ensure the new DAO architecture and object-based service logic are correctly compiled.</li>
</ul>

<hr>
<p align="center"><em>This project is maintained by Group 28. Version 1.6 focuses on clean architecture, professional data patterns, and the elimination of logic leaks.</em></p>
