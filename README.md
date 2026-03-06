<h1 align="center">MotorPH Payroll & HR System — NewGUI Version 1.2</h1>
<p align="center">
  <strong>Description:</strong> Stable release featuring enhanced self-service payslip generation and UI standardization.
</p>

<hr>

<h2>📝 Description</h2>
<ul>
  <li>Welcome to the stable release of the <b>MotorPH NewGUI (Version 1.2)</b>.</li>
  <li>This branch represents a significant milestone in the project's development, moving from a legacy gradient-based design to a professional, enterprise-grade interface.</li>
  <li>This system is designed to handle employee records, attendance, and payroll calculations for MotorPH.</li>
</ul>

<h2>🚀 What's New in Version 1.2</h2>
<ul>
  <li><b>Self-Service Payslip Printing:</b> Regular employees can now officially generate and print their own payslips directly from their dashboard.</li>
  <li><b>Unified Iconography:</b> Standardized the <b>Payslip Icon</b> across both Admin and Employee interfaces, ensuring a consistent and intuitive user experience.</li>
  <li><b>Enterprise Branding:</b> Retained the professional <b>MotorPH Blue</b> theme (<code>#1D458F</code>).</li>
  <li><b>Transparency Optimization:</b> Optimized the GUI component hierarchy via <code>setOpaque(false)</code> and overriding <code>paintComponent</code> for a seamless "layered" look.</li>
  <li><b>Stabilized Data Parsing:</b> Improved <code>parseMoney</code> logic to handle formatted numeric strings (e.g., "90,000") without runtime exceptions.</li>
</ul>

<h2>🛠 Technical Features (OOP Principles)</h2>
<ul>
  <li><b>Inheritance:</b> Custom UI classes extend <code>JPanel</code> or <code>JFrame</code> to modularize the interface.</li>
  <li><b>Encapsulation:</b> All file operations are handled via the <code>FileHandler</code> class, protecting the integrity of the underlying CSV data.</li>
  <li><b>Polymorphism:</b> Leveraged method overriding to ensure consistent button behavior and icon rendering across different user roles.</li>
  <li><b>Dynamic Rendering:</b> Implemented custom <code>TableCellRenderers</code> for a responsive table experience.</li>
</ul>

<h2>📋 Core Functionalities</h2>
<ul>
  <li><b>Role-Based Access Control:</b> Admin has full access to CRUD operations; Employees are restricted to personal records and the <b>Payslip Printing</b> module.</li>
  <li><b>Employee Management:</b> Searchable directory for viewing and updating employee records.</li>
  <li><b>Payroll Processing:</b> Automated modules for calculating monthly salary cycles, statutory contributions, and secure document generation.</li>
  <li><b>Attendance Tracking:</b> Visual logs for monitoring employee time-ins and leave requests.</li>
  <li><b>User Feedback:</b> Custom-styled modal dialogs for warnings and success messages.</li>
</ul>

<h2>⚙️ Development Setup</h2>
<ul>
  <li>Clone this specific branch: <code>git clone -b NewGUI-Version1.2 https://github.com/siejeihyung/MotorPH_OOP-Group-28.git</code></li>
  <li>Open the project in your preferred IDE (NetBeans/IntelliJ/Eclipse).</li>
  <li><b>Crucial:</b> Perform a <b>"Clean and Build"</b> to ensure all new icon assets and rendering updates are correctly compiled.</li>
  <li>Run <code>LoginPanel.java</code> to start the application.</li>
</ul>

<hr>
<p><em>This is still in process of updating.</em></p>
