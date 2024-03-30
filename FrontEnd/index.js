
  function loadTable() {

    fetch("http://localhost:8080/product", {
        
    }).then(response => {
        if (!response.ok) {
            throw new Error("Erro na requisicao")
        }
        return response.json();
    }).then(data => {

        var trHTML = "";
        for (let object of data) {
            trHTML += "<tr>";
            trHTML += "<td>" + object["id"] + "</td>";
            trHTML += "<td>" + object["name"] + "</td>";
            trHTML += "<td>" + object["unitPrice"] + "</td>";
            trHTML += "<td>" + object["quantity"] + "</td>";
            trHTML +=
              '<td><button type="button" class="btn btn-outline-secondary" onclick="showUserEditBox(' +
              object["id"] +
              ')">Edit</button>';
            trHTML += 
              '<button type="button" class="btn btn-outline-danger" onclick="userDelete(' +
              object["id"] +
              ')">Del</button></td>';
            trHTML += "</tr>";
          }
        
        document.getElementById("mytable").innerHTML = trHTML;
    }).catch(error => {
        console.error("Aconteceu um error", error);
    }) 
      
    };

  
  loadTable();

  function showUserCreateBox() {
    Swal.fire({
      title: "Create user",
      html:
        '<input id="id" type="hidden">' +
        '<input id="name" class="swal2-input" placeholder="Produto">' +
        '<input id="unitPrice" class="swal2-input" placeholder="Preço">' +
        '<input id="quantity" class="swal2-input" placeholder="Quantidade">',
      focusConfirm: false,
      preConfirm: () => {
        userCreate();
      },
    });
  }
  
  function userCreate() {
    const name = document.getElementById("name").value;
    const unitPrice = document.getElementById("unitPrice").value;
    const quantity = document.getElementById("quantity").value;
    
    let isValidForm = true;
    if (name == undefined || name.trim() == "") {
      alert("Campo nome deve ser preenchido");
      isValidForm = false;
    }

    if(unitPrice == undefined || unitPrice.trim() == "" && isValidForm) {
      alert("Campo Preço deve ser preenchido");
      isValidForm = false;

    } else if (isNaN(parseFloat(unitPrice)) && isValidForm) {
      alert("Campo Preço deve ser um valor válido como: 12.99");
      isValidForm = false;
    }

    if(quantity == undefined || quantity.trim() == "" && isValidForm) {
      alert("Campo Quantidade deve ser preenchido");
      isValidForm = false;
    } else if (isNaN(parseInt(quantity)) && isValidForm) {
      alert("Campo Quantidade deve ser númerico");
      isValidForm = false;
    }
    
    if (isValidForm) {
      fetch("http://localhost:8080/product", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            quantity: quantity,
            unitPrice: unitPrice
          })

      }).then(response => {
        if (!response.ok) {
            throw new Error("Aconteceu um erro");
        }
        return response.json()

      }).then(data => {
        loadTable();

      }).catch(error => {
        console.log("Aconteceu um error", error);
        });
      } else {
        showUserCreateBox();
      }
  }

  function showUserEditBox(id) {
    
    const url = "http://localhost:8080/product/" + id;
    console.log(url);

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error("ERROR");
        }

        return response.json();
    }).then(data => {
        Swal.fire({
            title: "Edit User",
            html:
              '<input id="id" type="hidden" value=' +
              data["id"] +
              ">" +
              '<input id="name" class="swal2-input" placeholder="Produto" value="' +
              data["name"] +
              '">' +
              '<input id="unitPrice" class="swal2-input" placeholder="Preço" value="' +
              data["unitPrice"] +
              '">' +
              '<input id="quantity" class="swal2-input" placeholder="Quantidade" value="' +
              data["quantity"] +
              '">',
            focusConfirm: false,
            preConfirm: () => {
              userEdit();
            },
          });
        console.log(data);
    }).catch(error => {
        console.error("Aconteceu um error", error);
    })

  }
  
  function userEdit() {
    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;
    const unitPrice = document.getElementById("unitPrice").value;
    const quantity = document.getElementById("quantity").value;

    let isValidForm = true;
    if (name == undefined || name.trim() == "") {
      alert("Campo nome deve ser preenchido" + name);
      isValidForm = false;
    }
    console.log("aqui" + name.trim() + "aqui");

    if(unitPrice == undefined || unitPrice.trim() == "" && isValidForm) {
      alert("Campo Preço deve ser preenchido");
      isValidForm = false;

    } else if (isNaN(parseFloat(unitPrice)) && isValidForm) {
      alert("Campo Preço deve ser um valor válido como: 12.99");
      isValidForm = false;
    }

    if(quantity == undefined || quantity.trim() == "" && isValidForm) {
      alert("Campo Quantidade deve ser preenchido");
      isValidForm = false;
    } else if (isNaN(parseInt(quantity)) && isValidForm) {
      alert("Campo Quantidade deve ser númerico");
      isValidForm = false;
    }
    
    if (isValidForm) {
      fetch("http://localhost:8080/product/", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            name: name,
            unitPrice: unitPrice,
            quantity: quantity
          })
    }).then(response => {
        if (!response.ok) {
            throw new Error("ERROR");
        }
        
        return response.json();
    }).then(data => {
        loadTable();

    }).catch(error => {
        console.error("Aconteceu um error", error);
    })
    } else {
      showUserEditBox(id);
    }

  }

  function userDelete(id) {

    fetch("http://localhost:8080/product/" + id, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error("ERROR");
        }

        return response.json();
    }).catch(data => {
        loadTable();
    }).catch(error => {
        console.error("Aconteceu um error", error);
    })
  }