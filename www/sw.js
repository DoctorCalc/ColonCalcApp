const CACHE_NAME = 'coloncalc-v1.0.9'; 

const ASSETS = [
  './',
  './index.html',
  './Calculadora.html',
  './Riesgo_CCR.html',
  './js/tailwind.min.js',
  './fonts/poppins-v24-latin-500.woff2', 
  './fonts/poppins-v24-latin-600.woff2',
  './fonts/poppins-v24-latin-700.woff2',
  './fonts/poppins-v24-latin-800.woff2',
  './fonts/poppins-v24-latin-regular.woff2',
  './manifest.json',
  './icons/icon-48.png',
  './icons/icon-192.png',  // <--- Añadido
  './icons/icon-512.png'   // <--- Añadido
];

// Instalar y guardar en caché
self.addEventListener('install', e => {
  e.waitUntil(
    caches.open(CACHE_NAME).then(cache => {
      console.log('Cacheando archivos de ColonCalc...');
      return cache.addAll(ASSETS);
    })
  );
});

// Responder desde la caché si no hay internet
self.addEventListener('fetch', e => {
  e.respondWith(
    caches.match(e.request).then(res => {
      return res || fetch(e.request);
    })
  );
});