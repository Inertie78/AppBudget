# AppBudget
Application de gestion d'un budget personnel.

## Cahier des charges de l'application

L'application permettra à l'utilisateur de créer et de gérer un petit budget individuel, par exemple, pour le ménage ou pour des vacances. 

Il y aura un menu "File", qui aura 4 items; "New", "Open", "Save", "Quit". Ceci permettra de créer, de modifier plusieurs budgets. Le nom du budget sera afficher dans le cadre de le fenêtre de l'application. 

L'application permettra d'écrire l'objet de la saisie, par un champ de "Libellé", un champ "Montant" et un champ "Date".

Une fois l'objet saisi, il faudra cliquer sur le bouton "Crédit" ou sur le bouton "Débit" pour enregistrer l'objet dans un fichier. 

Dans la partie de droite, il y aura une table qui permettra de visualiser les objets saisis, leurs libellés, ainsi que de supprimer une entrée si on le souhaite. 

Au bas de la table, nous afficherons les totaux des crédits et des débits, ainsi que le solde. 

Sur le haut de la table, se trouvera un menu déroulant qui permettra de sélectionner ou de filtrer les dates des objets que l'on souhaite afficher.

### Schema de l'application

![Schéma de l'application.](./schema.jpg "Schéma du l'appilcation pour la gestion d'un budget personnel.")

Nous avons en :
  1) Le nom du budget 
  2) Un menu pour créer , ouvrir, sauvegarder un budget et quitter l'appliaction
  3) Le libellé de la transaction.
  4) Le montant de la transaction.
  5) La date de la transaction.
  6) Le type de la transaction.
  7) Tableau du budget personnel avec :\
      7.1 les totaux des actifs et passifs plus, le solde du compte\
      7.2 la table qui affiche les saisies\
      7.3 le sélecteur de filtre

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