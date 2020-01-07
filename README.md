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

Εμεις αντιμετωπισαμε καποια error τα οποια λυθηκαν μετα το παρακατω query (να εκτελεστει μεσα στην βαση δεδομενων που θα χρησιμοποιηθει)

```bash
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
```

Η πρωτη λεξη 'root' να αντικατασταθει αν χρησιμοποιειται καποιος user στην βαση δεδομενων. Η δευτερη λεξη root να αντικατασταθει με το password του user.

Να δημιουργηθει ο admin για να μπορει να γινει εισοδος στο συστημα

```bash
insert into officers (username, password, role) values ('officer1', '1234', 'Admin');
```

Μετα απο αυτα τα βηματα ειναι ετοιμο το σηκωμα της εφαρμογης σε εναν apache server.

## User Manual



