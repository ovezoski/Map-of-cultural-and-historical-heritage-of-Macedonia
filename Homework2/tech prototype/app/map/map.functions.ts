export function capitalizeAndReplace(category: string) {
  const cleanedString = category.replace(/[_\s]+/g, " ");

  const words = cleanedString.split(" ");

  const capitalizedString = words
    .map((word) => {
      if (word == "of") return word;
      return word.charAt(0).toUpperCase() + word.slice(1);
    })
    .join(" ");

  return capitalizedString;
}

const degToRad = (deg: number) => deg * (Math.PI / 180);

export function calculateDistance(
  lt1: string,
  ln1: string,
  lt2: string,
  ln2: string
) {
  const earthRadius = 6371; // Radius of the Earth in kilometers

  const lat1 = Number(lt1);
  const lon1 = Number(ln1);
  const lat2 = Number(lt2);
  const lon2 = Number(ln2);

  const dLat = degToRad(lat2 - lat1);
  const dLon = degToRad(lon2 - lon1);

  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(degToRad(lat1)) *
      Math.cos(degToRad(lat2)) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);

  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = earthRadius * c; // Distance in kilometers

  return Math.round(distance);
}
