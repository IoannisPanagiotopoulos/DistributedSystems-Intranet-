# ds-2019-internal

## ΟΜΑΔΑ

Βασίλειος Μάντζαρης  
​Α.Μ. 21649
Ιωάννης ​​Μακρής  
​Α.Μ. 21526 
Ιωάννης Παναγιωτόπουλος ​
Α.Μ. 217120

## Installation Manual

Το πρώτο βήμα ειναι το στήσιμο του MYSQL Database.
Το schema της βασης βρισκεται στο αρχειο src/db-schema.sql

Να δημιουργηθει ενα database. Στην συγκεκριμενη περιπτωση ονομαζεται john.

Εμεις αντιμετωπισαμε καποια error τα οποια λυθηκαν μετα το παρακατω query (να εκτελεστει μεσα στην βαση δεδομενων που θα χρησιμοποιηθει).

```bash
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
```

Η πρωτη λεξη 'root' να αντικατασταθει αν χρησιμοποιειται καποιος user στην βαση δεδομενων. Η δευτερη λεξη root να αντικατασταθει με το password του user.

Να δημιουργηθει ο admin για να μπορει να γινει εισοδος στο συστημα.

```bash
insert into officers (username, password, role) values ('officer1', '1234', 'Admin');
```
Να ανανεωθει με τα σωστα στοχεια το αρχειο src/hibernate.cfg.xml 

*Στο connection.url να αντικατασταθει η λεξη john μετα το port, με το ονομα της δικια σας βασης.

*Στο connection.username να συμπληρωθει το username του user.

*Στο connection.password να συμπληρωθει το password του user.

Μετα απο αυτα τα βηματα ειναι ετοιμο το σηκωμα της εφαρμογης σε εναν apache server.

## User Manual

Σηκωνουμε την εφαρμογη και προχωραμε στο παρακατω link.

```bash
http://localhost:8080/spring-mvc-1/
```

Συμπληρωνουμε τα στοιχεια του admin που δημιουργησαμε προηγουμενως και κανουμε login.

Ο admin του συστηματος διαχειριζεται τον κατάλογο χρηστών. Στην σελιδα που εμφανιζεται μπορουμε να κανουμε ενεργειες crud(create read update 
delete) για τους διοικητικους υπαλληλους (απο εδω και περα officers) και φοιτητες.

Αρχικα να δημιουργηθει ενας Officer τυπου Supervisor. Θα πρεπει να επιλεχθει το department All καθως επιβλεπει ολα τα τμηματα.

Στην συνεχεια να δημιουργηθουν 4 Officers τυπου Officer(δηλαδη απλοι υπαλληλοι), ενας για καθε τμημα.

Στην συνεχεια να δημιουργηθουν μερικοι students (2ο πινακακι) για καθε τμημα. Θα πρεπει ολοι οι φοιτητες να ειναι ανενεργοι.

Με την επιλογη logout (πανω δεξια) κανουμε αποσυνδεση.

Με την συνδεση ενος υπαλληλου (απλου) μπορουμε να ενεργοποιησουμε τους φοιτητες του τμηματος που επιβλεπει. Ας ενεργοποιησουμε ολους τους φοιτητες που δημιουργησαμε, κανωντας καθε φορα login με τον υπαλληλο που επιβλεπει το τμημα του.

Με την συνδεση ενος supervisor εχουμε δικαιώματα read ολων των φοιτητων. Στην δευτερη σελίδα ( Application Status ) μπορουμε να δουμε την κατασταση καθε τμηματος. Αν ενα τμημα ειναι ενεργο τοτε το εξωτερικο συστημα επιτρεπει στον φοιτητη απο το τμημα αυτο να αποστειλει αιτηση σιτησης.

Ας ενεργοποιησουμε ενα τμημα αφου πρωτα βαλουμε ενα ποσοστο. Εστω οτι ενεργοποιουμε το τμημα πληροφορικης.

Στην συνεχεια θα δημιουργησουμε μια φορμα σιτησης για εναν φοιτητη πληροφορικης. Αυτο κανονικα γινεται μεσω του εξωτερικου συστηματος οποτε για τωρα θα το δημιουργησουμε απο την βαση δεδομενων.

Να εκτελεστει το παρακατω query.

```bash
insert into applicationDetails (email, username, dept, fullName, city, income, familyIncome, parent1_employmentStatus, parent2_employmentStatus, siblingsStudents)
	values ('q@q.q', 'student1', 'Informatics', 'Vasilis', 'Athens', 0, '20000', 'emp', 'emp', 1);
```

Να αντικατασταθουν τα στοιχεια του φοιτητη με τα στοιχεια του φοιτητη που δημιουργησατε εσεις απο το τμημα πληροφορικης.

Στην συνεχεια μπορουμε να κανουμε login με τον απλο officer που διαχειριζεται το τμημα πληροφορικης.

Στην δευτερη σελιδα (Applications) θα εμφανιστει τωρα η αιτηση σιτησης την οποια αποδεχεται η απορριπτει ο υπαλληλος.

Αποδεχομαστε την αιτηση.

Μπορουμε να επαναλαβουμε την διαδικασια και για περισσοτερους φοιτητηες πληροφορικης.

Αφου αποδεχτουμε ολες τις αιτησεις ή/και φτασει η ημερομηνια που τερματιζει η διαδικασια αιτησεων, ο προισταμενος υπαλληλος απενεργοποιει το status του department που ενεργοποιησαμε προηγουμενως.

Κατα την απενεργοποιηση μπορουμε να δουμε στην βαση δεδομενων οτι οι φοιτητες παιρνουν τους καταλληλους ποντους.

Η καταταξη των φοιτητων θα φαινεται στο εξωτερικο συστημα οταν υλοποιηθει.
