# AppBudget
Application de gestion d'un budget personnel.

### Schema de l'application

![Schéma de l'application.](./schema.jpg "Schéma du l'appilcation pour la gestion d'un budget personnel.")

Nous avons en :
  1) Un menu pour new, ouvrir, sauvegarder un budget et quitter l'appliaction
  2) Le libellé de la transaction.
  3) Le montant de la transaction.
  4) La date de la transaction.
  5) Le type de la transaction.
  6) Tableau du budget personnel avec les totaux des actifs et passifs plus, le solde du compte.

### Planning

![Planning de l'application.](./planning.png "Planning de l'appilcation pour la gestion d'un budget personnel.")

### Structure du projet
```
└── Personal_Budget
    ├── .gitignore
    ├── schema.jpg
    ├── planning.png
    ├── README.md
    ├── Application.java
    │   └──  constructor method
    ├── WindowPrincipal.java
    │   ├── constructor method
    │   └── actionPerformed method
    ├── WindowMenu.java
    │   └──  constructor method
    ├── WindowTable.java
    │   ├── constructor method
    │   ├── AddValue method
    │   └── RemoveValue method
    ├── WindowButton.java
    │   └─ constructor method
    └── OpenAndSave.java
        ├── NewFile method
        ├── OpenFile method
        └── SaveFile method
```