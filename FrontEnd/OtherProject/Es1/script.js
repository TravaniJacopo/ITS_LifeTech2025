document.querySelector("#Aggiungi").addEventListener("click", function () {
  const value = document.querySelector("#input").value;
  if (value ==0) {
    alert("Inserisci un valore valido");
    } else {   
        console.log(value);
        const li = document.createElement("li");
        li.textContent = value;
        document.querySelector("#list").appendChild(li);
        document.querySelector("#input").value = "";
        const button = document.createElement("button");
        button.className = "material-icons";
        button.textContent = 'delete'  ;
        li.appendChild(button);
        button.addEventListener("click", function () {
          li.remove();
        });

        const btnUp = document.createElement("button");
        btnUp.className = "material-icons";
        btnUp.textContent = 'arrow_upward';
        
    }

}
);