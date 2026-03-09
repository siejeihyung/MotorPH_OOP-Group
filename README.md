<h1 align="center">MotorPH Payroll & HR System — NewGUI Version 1.4.2</h1>
<p align="center">
<strong>Description:</strong> Enterprise-grade update featuring Full RBAC Implementation, Multi-Role Dashboards, and Employee Self-Service.
</p>

<hr>

<h2>📝 Description</h2>
<ul>
<li>Welcome to the official release of <b>MotorPH NewGUI (Version 1.4)</b>.</li>
<li>This milestone marks the transition to a sophisticated <b>Role-Based Access Control (RBAC)</b> system and introduces comprehensive self-service modules for leave and IT support.</li>
<li>The system has been refactored to prioritize data integrity by moving all legacy <code>.txt</code> storage to standardized <b>CSV</b> formats.</li>
</ul>

<h2>🚀 What's New in Version 1.4.2</h2>
<ul>
<li><b>Employee Self-Service (Leave & Support):</b>
<ul>
<li><b>Leave Management:</b> Submit vacation/sick leave requests and access a real-time <b>Leave History</b> log.</li>
<li><b>IT Support Ticketing:</b> Users can now file support tickets directly through the dashboard, with a full <b>Ticket History</b> feature to track resolution status.</li>
</ul>
</li>
<li><b>Full RBAC Implementation:</b> Intelligent routing for 4 roles (<b>ADMIN, HR, FINANCE, and EMPLOYEE</b>) via <code>credentials.csv</code>.</li>
<li><b>Integrated Support Dashboard:</b> A centralized IT Support interface accessible across all roles to streamline system troubleshooting.</li>
<li><b>Logic & Reporting Cleanup:</b> Removed legacy metrics (Late Penalties/Overtime) in <code>ViewEmployeePanel</code> to focus on core financial accuracy.</li>
</ul>

<h2>🛠 Technical Features (OOP Principles)</h2>
<p>The system leverages core Java OOP principles to manage complex data flow and role segregation:</p>

<ul>
<li><b>Encapsulation:</b> File I/O for tickets and leave logs is centralized in <code>FileHandler.java</code>, ensuring <b>CSV</b> data is handled securely.</li>
<li><b>Abstraction:</b> The <code>Employee</code> abstract class and <code>IPayable</code> interface provide a blueprint for all employee types, hiding internal calculation complexities.</li>
<li><b>Inheritance:</b> Custom UI components for dashboards and ticket forms extend base layout classes to ensure consistency.</li>
<li><b>Polymorphism:</b> Overridden methods ensure that dashboard navigation and ticket processing behave correctly based on the user's role.</li>
<li><b>Composition:</b> The <code>Employee</code> class uses <code>Benefits</code> and <code>Ticket</code> objects to maintain modularity.</li>
</ul>

<h2>📋 System Roles & Access</h2>
<table border="1" style="width:100%; border-collapse: collapse; text-align: left;">
<tr style="background-color: #f2f2f2;">
<th>Role</th>
<th>Primary Access</th>
</tr>
<tr>
<td><b>ADMIN</b></td>
<td>Full system CRUD, role/ticket management, and global reporting.</td>
</tr>
<tr>
<td><b>HR</b></td>
<td>Employee lifecycle management and leave request auditing.</td>
</tr>
<tr>
<td><b>FINANCE</b></td>
<td>Salary computation, deduction auditing, and payslip generation.</td>
</tr>
<tr>
<td><b>EMPLOYEE</b></td>
<td>Profile viewing, Leave Filing, and IT Support Ticket history.</td>
</tr>
</table>

<h2>⚠️ Current Known Issues & Roadmap</h2>
<ul>
<li><b>Dashboard Design:</b> While functional, the UI theme requires further polishing to ensure a consistent, professional aesthetic across the new IT Support and Ticket dashboards.</li>
<li><b>Ticket Format Redesign:</b> The current ticket support format is being audited for a total redesign to better integrate ticket tracking across Admin, HR, Finance, and Employee roles.</li>
<li><b>Design Standardization:</b> Ongoing efforts to align all dashboard modules with the "MotorPH Blue" theme (<code>#1D458F</code>) for a cohesive user experience.</li>
</ul>

<h2>⚙️ Development Setup</h2>
<ul>
<li><b>Clone Branch:</b> <code>git clone -b NewGUI-Version1.4 https://github.com/siejeihyung/MotorPH_OOP-Group-28.git</code></li>
<li><b>CSV Integrity:</b> Ensure all files in the <code>data/</code> folder (employee, attendance, credentials, tickets, and leave_history) use the <code>.csv</code> extension.</li>
<li><b>Clean and Build:</b> Perform a clean build to compile the updated ticket history and leave management logic.</li>
</ul>

<hr>
<p align="center"><em>This project is maintained by SunnyEljohn and Group 28. Version 1.4 focuses on professional role segregation and system-wide support features.</em></p>
