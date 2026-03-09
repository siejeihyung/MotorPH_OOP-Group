<h1 align="center">MotorPH Payroll & HR System — NewGUI Version 1.4.4</h1>
<p align="center">
<strong>Description:</strong> Enterprise-grade update featuring Full RBAC Implementation, Multi-Role Dashboards, and a Production-Ready IT Ticketing Engine.
</p>

<hr>

<h2>📝 Description</h2>
<ul>
<li>Welcome to the official release of <b>MotorPH NewGUI (Version 1.4.4)</b>.</li>
<li>This milestone completes the <b>IT Specialist</b> workflow, integrating real-time ticket management with a modernized, Finance-aligned user interface.</li>
<li>The system now features full <b>Two-Way Data Persistence</b> for tickets, moving from static viewing to active CSV state management.</li>
</ul>

<h2>🚀 What's New in Version 1.4.4</h2>
<ul>
<li><b>Production-Ready IT Support Dashboard:</b>
<ul>
<li><b>UI Alignment:</b> The IT Specialist environment has been fully redesigned to match the "MotorPH Blue" corporate theme used in the Finance and Admin modules.</li>
<li><b>Real-Time Filtering:</b> Added a <b>Live Search/Filter</b> bar allowing IT staff to instantly sort tickets by Sender, ID, or Category.</li>
<li><b>Two-Way Persistence:</b> Integrated <code>TicketDAO</code> with auto-save capabilities; ticket status updates (Open, In Progress, Resolved) now write directly to <code>tickets.csv</code>.</li>
<li><b>Summary Analytics:</b> Added top-level summary cards providing instant counts of Total, Open, and Resolved tickets.</li>
</ul>
</li>
<li><b>Expanded RBAC:</b> Intelligent routing now supports 5 roles: <b>ADMIN, HR, FINANCE, EMPLOYEE, and IT SPECIALIST</b> via <code>credentials.csv</code>.</li>
<li><b>Logic Optimization:</b> Refactored <code>TicketDAO</code> with robust error handling and flexible pathing to prevent "File Not Found" errors across different IDE environments.</li>
</ul>

<h2>🛠 Technical Features (OOP Principles)</h2>
<ul>
<li><b>Encapsulation:</b> Ticket data is managed through <code>TicketDAO.java</code>, ensuring <b>CSV</b> data is accessed only through secure, controlled methods with proper regex-based comma handling.</li>
<li><b>Abstraction:</b> The UI interaction layer is decoupled from file storage via the DAO pattern, allowing for future SQL integration without breaking the View layer.</li>
<li><b>Inheritance:</b> Action buttons and navigation components leverage custom UI factories to maintain design consistency across all dashboards.</li>
<li><b>Polymorphism:</b> Table row sorters and regex filters dynamically adapt to user input for optimized data searching.</li>
<li><b>Composition:</b> The <code>ITSupportDashboard</code> integrates <code>DefaultTableModel</code> and <code>TableRowSorter</code> to handle complex data presentation.</li>
</ul>

<h2>📋 System Roles & Access</h2>
<table border="1" style="width:100%; border-collapse: collapse; text-align: left;">
<tr style="background-color: #f2f2f2;">
<th>Role</th>
<th>Primary Access</th>
</tr>
<tr>
<td><b>ADMIN</b></td>
<td>Full system CRUD, role/ticket management, and global report viewing.</td>
</tr>
<tr>
<td><b>HR</b></td>
<td>Employee lifecycle management and leave auditing.</td>
</tr>
<tr>
<td><b>FINANCE</b></td>
<td>Salary computation and payslip generation.</td>
</tr>
<tr>
<td><b>IT SPECIALIST</b></td>
<td><b>Ticketing System Management</b>, Status Lifecycle Control, and Audit Logs.</td>
</tr>
<tr>
<td><b>EMPLOYEE</b></td>
<td>Profile, Leave Filing, and IT Support Ticket history.</td>
</tr>
</table>

<h2>⚠️ Current Known Issues & Roadmap</h2>
<ul>
<li><b>Notification System:</b> Developing an alert system to notify IT Specialists when a new high-priority ticket is filed.</li>
<li><b>Export Feature:</b> Planning a CSV/PDF export for the Ticket History log for monthly IT performance audits.</li>
<li><b>Attachments:</b> Investigating support for screenshot/file attachments within the ticketing module.</li>
</ul>

<h2>⚙️ Development Setup</h2>
<ul>
<li><b>Clone Branch:</b> <code>git clone -b NewGUI-Version1.4.4 https://github.com/siejeihyung/MotorPH_OOP-Group-28.git</code></li>
<li><b>CSV Integrity:</b> Ensure <code>tickets.csv</code> is located in <code>src/data/</code> or the project root. The system will automatically detect the active path.</li>
<li><b>Build:</b> Perform a <b>"Clean and Build"</b> to ensure the new Ticket persistence logic and UI assets are correctly compiled.</li>
</ul>

<hr>
<p align="center"><em>This project is maintained by SunnyEljohn and Group 28. Version 1.4.4 focuses on UI consistency and data persistence.</em></p>
