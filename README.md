<h1 align="center">MotorPH Payroll & HR System — NewGUI Version 1.3</h1>
<p align="center">
  <strong>Description:</strong> Major update featuring CSV data migration, enhanced self-service payslip generation, and UI standardization.
</p>

<hr>

<h2>📝 Description</h2>
<ul>
  <li>Welcome to the official release of the <b>MotorPH NewGUI (Version 1.3)</b>.</li>
  <li>This milestone represents a complete shift in the project's data architecture, moving from legacy <code>.txt</code> storage to standardized <b>CSV (Comma-Separated Values)</b> for improved data integrity and easier external reporting.</li>
  <li>This system remains the core solution for managing MotorPH employee records, attendance tracking, and automated payroll calculations.</li>
</ul>

<h2>🚀 What's New in Version 1.3</h2>
<ul>
  <li><b>CSV Data Migration:</b> All backend storage has been refactored from <code>.txt</code> to <code>.csv</code>. This allows for better data organization and compatibility with external spreadsheet software.</li>
  <li><b>Self-Service Payslip Printing:</b> Regular employees can now officially generate and print their own payslips directly from their dashboard without administrative assistance.</li>
  <li><b>Unified Iconography:</b> Standardized the <b>Payslip Icon</b> across both Admin and Employee interfaces, ensuring a consistent and professional user experience.</li>
  <li><b>Enterprise Branding:</b> Refined the professional <b>MotorPH Blue</b> theme (<code>#1D458F</code>) for a more modern, "layered" look.</li>
  <li><b>Robust Data Parsing:</b> Updated <code>parseMoney</code> and file-reading logic to handle comma-separated values and formatted numeric strings (e.g., "90,000") without runtime errors.</li>
</ul>

<h2>🛠 Technical Features (OOP Principles)</h2>
<ul>
  <li><b>Inheritance:</b> Custom UI components extend <code>JPanel</code> or <code>JFrame</code> to maintain a modular and reusable interface.</li>
  <li><b>Encapsulation:</b> File I/O operations are now centralized within the <code>FileHandler</code>, strictly managing how <b>CSV</b> data is read and written.</li>
  <li><b>Abstraction:</b> The UI layer interacts with data models without needing to manage the underlying file format, simplifying future updates.</li>
  <li><b>Polymorphism:</b> Overridden methods ensure consistent button behavior and icon rendering across different user roles (Admin vs. Employee).</li>
</ul>

<h2>📋 Core Functionalities</h2>
<ul>
  <li><b>Role-Based Access Control:</b> Admins maintain full CRUD (Create, Read, Update, Delete) permissions; Employees have access to personal data and the <b>Payslip Printing</b> module.</li>
  <li><b>Employee Management:</b> A fully searchable directory for viewing and managing standardized employee records.</li>
  <li><b>Automated Payroll:</b> Modules for calculating salary cycles, tax deductions, and statutory contributions synced directly with the CSV backend.</li>
  <li><b>Attendance Tracking:</b> Visual logs for monitoring time-ins/outs and leave requests.</li>
</ul>

<h2>⚙️ Development Setup</h2>
<ul>
  <li>Clone this specific branch: <code>git clone -b NewGUIVersion1.2 https://github.com/siejeihyung/MotorPH_OOP-Group-28.git</code></li>
  <li>Open the project in your preferred IDE (NetBeans/IntelliJ/Eclipse).</li>
  <li><b>Crucial:</b> If migrating from an older version, ensure all <code>.txt</code> files are renamed to <code>.csv</code> in your project directory.</li>
  <li>Perform a <b>"Clean and Build"</b> to ensure all new icon assets and CSV parsing updates are correctly compiled.</li>
  <li>Run <code>LoginPanel.java</code> to start the application.</li>
</ul>

<hr>
<p align="center"><em>This project is currently in active development. Version 1.3 focuses on data standardization and UI stability.</em></p>
