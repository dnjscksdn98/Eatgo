(async () => {
  const url = "http://localhost:8181/restaurants";
  const response = await fetch(url);
  const restaurants = await response.json();
  console.log(restaurants);

  const element = document.getElementById("app");
  element.innerHTML = `
    ${restaurants.map(restaurant => `
      <p>
        ${restaurant.name}
        ${restaurant.address}
      </p>
    `).join('')}
  `;
})();