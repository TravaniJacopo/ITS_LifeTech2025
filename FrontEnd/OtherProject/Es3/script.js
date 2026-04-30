const articoli = [
    { id: 1, descrizione: "Articolo 1", prezzo: 10 },
    { id: 2, descrizione: "Articolo 2", prezzo: 15 },
    { id: 3, descrizione: "Articolo 3", prezzo: 7 },
    { id: 4, descrizione: "Articolo 4", prezzo: 22 }
];
  
const carrello = [];

const listaArticoli = document.getElementById("lista-articoli");
const listaCarrello = document.getElementById("lista-carrello");
const totale = document.getElementById("totale-carrello");


for (const articolo of articoli) {
    const li = document.createElement("li");
    li.textContent = `${articolo.descrizione} - €${articolo.prezzo}`;

    const button = document.createElement("button");
    button.textContent = "Aggiungi al carrello";

    button.addEventListener("click", () => {
        const articoloNelCarrello = carrello.find(a => a.id === articolo.id);

        if (articoloNelCarrello) {
            articoloNelCarrello.quantita++;
        } else {
            carrello.push({...articolo, quantita: 1});
        }

        aggiornaCarrello();
    });

    li.appendChild(button);
    listaArticoli.appendChild(li);
}


function aggiornaCarrello() {
    listaCarrello.innerHTML = "";
    let totaleCarrello = 0;

    for (const articolo of carrello) {
        const li = document.createElement("li");

        const testo = document.createElement("span");
        testo.textContent = `${articolo.descrizione} - €${articolo.prezzo}`;
        li.appendChild(testo);

        totaleCarrello += articolo.prezzo * articolo.quantita;

        const btnPiu = document.createElement("button");
        btnPiu.textContent = "+";

        btnPiu.addEventListener("click", () => {
            articolo.quantita++;
            aggiornaCarrello();
        });

        const btnMeno = document.createElement("button");
        btnMeno.textContent = "-";

        btnMeno.addEventListener("click", () => {
            if (articolo.quantita > 1) {
                articolo.quantita--;
            }
            aggiornaCarrello();
        });

        const btnElimina = document.createElement("button");
        btnElimina.textContent = "Elimina";

        btnElimina.addEventListener("click", () => {
            const index = carrello.indexOf(articolo);
            carrello.splice(index, 1);
            aggiornaCarrello();
        });



        const textquantita = document.createElement("span");
        textquantita.textContent = ` Quantità: ${articolo.quantita} `;
        li.appendChild(btnPiu);
        li.appendChild(textquantita);
        li.appendChild(btnMeno);
        li.appendChild(btnElimina);
        listaCarrello.appendChild(li);
    }

    totale.textContent = `Totale: €${totaleCarrello}`;
}