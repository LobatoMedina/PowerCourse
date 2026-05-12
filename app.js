/**
 * ═══════════════════════════════════════════════════════════════
 * EDUCORE ADMIN — app.js
 * Lógica frontend SPA: Login, Navegación, Módulos CRUD (mock)
 * 
 * Arquitectura:
 *  - State: objeto central de estado de la aplicación
 *  - Router: navegación entre vistas y módulos
 *  - Módulos: Profesores, Cursos, Analíticas
 *  - Components: funciones de renderizado reutilizables
 * ═══════════════════════════════════════════════════════════════
 */

'use strict';

/* ────────────────────────────────────────
   ESTADO GLOBAL DE LA APLICACIÓN
──────────────────────────────────────── */
const State = {
  /**
   * TOKEN JWT:
   * Al implementar auth real, guardar con:
   *   localStorage.setItem('jwt_token', token);
   * Y leer con:
   *   localStorage.getItem('jwt_token');
   * Por ahora, simulamos la sesión en memoria.
   */
  isAuthenticated: false,
  token: null,            // <- aquí irá el JWT real

  profesores: [],         // array de { id, nombre, email, createdAt }
  cursos: [],             // array de { id, nombre, profesorId, thumbnailUrl }
  nextProfId: 1,
  nextCursoId: 1,

  /**
   * Mock de calificaciones — reemplazar con fetch al backend
   */
  ratings: [
    { cursoId: 1, nombre: 'Diseño UX Avanzado', rating: 4.8 },
    { cursoId: 2, nombre: 'Python para Datos',  rating: 4.5 },
    { cursoId: 3, nombre: 'React & Next.js',    rating: 4.0 },
    { cursoId: 4, nombre: 'Marketing Digital',  rating: 3.5 },
    { cursoId: 5, nombre: 'Fotografía Creativa', rating: 4.5 },
  ],
};


/* ════════════════════════════════════════
   ROUTER — Navegación de vistas / módulos
════════════════════════════════════════ */
const Router = {
  /** Cambia entre #view-login y #view-dashboard */
  showView(viewId) {
    document.querySelectorAll('.view').forEach(v => v.classList.remove('active'));
    const view = document.getElementById(`view-${viewId}`);
    if (view) view.classList.add('active');
  },

  /** Activa el módulo del dashboard */
  showModule(moduleId) {
    // Módulos
    document.querySelectorAll('.module').forEach(m => m.classList.remove('active'));
    const mod = document.getElementById(`module-${moduleId}`);
    if (mod) mod.classList.add('active');

    // Nav items
    document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));
    const navItem = document.querySelector(`.nav-item[data-module="${moduleId}"]`);
    if (navItem) navItem.classList.add('active');

    // Breadcrumb
    const bc = document.getElementById('breadcrumb-current');
    if (bc) bc.textContent = moduleId;

    // Inicializar módulo si es necesario
    if (moduleId === 'analiticas') Analiticas.render();
  },
};


/* ════════════════════════════════════════
   HELPERS
════════════════════════════════════════ */
const Helpers = {
  formatDate(date) {
    return new Intl.DateTimeFormat('es-MX', {
      day: '2-digit', month: 'short', year: 'numeric',
    }).format(date);
  },

  showAlert(elId, msgElId, msg, type = 'error') {
    const el = document.getElementById(elId);
    if (!el) return;
    if (msgElId) {
      const msgEl = document.getElementById(msgElId);
      if (msgEl) msgEl.textContent = msg;
    }
    el.style.display = 'flex';
    setTimeout(() => { el.style.display = 'none'; }, 4000);
  },

  hideAlert(elId) {
    const el = document.getElementById(elId);
    if (el) el.style.display = 'none';
  },

  /**
   * Genera estrellas SVG/Unicode para un rating dado.
   * Soporta medias estrellas (ej: 4.5).
   * @param {number} rating - Valor entre 0.5 y 5.0
   * @returns {string} HTML de estrellas
   */
  renderStars(rating) {
    let html = '';
    for (let i = 1; i <= 5; i++) {
      if (rating >= i) {
        html += '<span class="star filled">★</span>';
      } else if (rating >= i - 0.5) {
        html += '<span class="star half">★</span>';
      } else {
        html += '<span class="star">★</span>';
      }
    }
    return html;
  },

  /** Obtiene nombre de profesor por ID */
  getProfesorNombre(id) {
    // Primero buscar en el state (profesores creados)
    const found = State.profesores.find(p => p.id == id);
    if (found) return found.nombre;
    // Si es un mock estático del select de cursos
    const mocks = {
      'mock-1': 'Dr. Ana Martínez (mock)',
      'mock-2': 'Lic. Carlos Rueda (mock)',
      'mock-3': 'Ing. Sofia Vela (mock)',
    };
    return mocks[id] || '—';
  },
};


/* ════════════════════════════════════════
   MÓDULO: LOGIN
════════════════════════════════════════ */
const Login = {
  init() {
    const btn = document.getElementById('btn-login');
    if (btn) btn.addEventListener('click', () => this.handleLogin());

    // Enter en los inputs también envía
    ['login-email', 'login-password'].forEach(id => {
      const el = document.getElementById(id);
      if (el) el.addEventListener('keydown', e => {
        if (e.key === 'Enter') this.handleLogin();
      });
    });
  },

  async handleLogin() {
    const email    = document.getElementById('login-email')?.value.trim();
    const password = document.getElementById('login-password')?.value;
    const btn      = document.getElementById('btn-login');

    // Validación básica
    if (!email || !password) {
      Helpers.showAlert('login-error', 'login-error-msg', 'Ingresa email y contraseña.');
      return;
    }

    // Estado de carga
    btn.disabled = true;
    btn.querySelector('.btn__label').textContent = 'Verificando...';

    try {
      /**
       * ════════════════════════════════════════
       * TODO: Reemplazar simulación con llamada real:
       *
       * const res = await fetch('/api/auth/login', {
       *   method: 'POST',
       *   headers: { 'Content-Type': 'application/json' },
       *   body: JSON.stringify({ email, password })
       * });
       *
       * if (!res.ok) throw new Error('Credenciales inválidas');
       *
       * const { token } = await res.json();
       * localStorage.setItem('jwt_token', token);
       * State.token = token;
       * ════════════════════════════════════════
       */

      // SIMULACIÓN: espera 800ms y redirige
      await new Promise(resolve => setTimeout(resolve, 800));

      State.isAuthenticated = true;
      State.token = 'mock-jwt-token-xxxx'; // placeholder

      Router.showView('dashboard');
      Router.showModule('profesores');

    } catch (err) {
      Helpers.showAlert('login-error', 'login-error-msg', err.message || 'Error de autenticación.');
    } finally {
      btn.disabled = false;
      btn.querySelector('.btn__label').textContent = 'Ingresar al Panel';
    }
  },
};


/* ════════════════════════════════════════
   MÓDULO: PROFESORES
════════════════════════════════════════ */
const Profesores = {
  init() {
    document.getElementById('btn-add-profesor')
      ?.addEventListener('click', () => this.handleAdd());

    document.getElementById('btn-refresh-profs')
      ?.addEventListener('click', () => this.fetchAll());

    this.renderTable();
  },

  async handleAdd() {
    const nombre   = document.getElementById('prof-nombre')?.value.trim();
    const email    = document.getElementById('prof-email')?.value.trim();
    const password = document.getElementById('prof-password')?.value;
    const btn      = document.getElementById('btn-add-profesor');

    Helpers.hideAlert('prof-error');
    Helpers.hideAlert('prof-success');

    if (!nombre || !email || !password) {
      Helpers.showAlert('prof-error', 'prof-error-msg', 'Todos los campos son requeridos.');
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      Helpers.showAlert('prof-error', 'prof-error-msg', 'Ingresa un email válido.');
      return;
    }

    btn.disabled = true;
    btn.querySelector('.btn__label').textContent = 'Guardando...';

    try {
      /**
       * ════════════════════════════════════════
       * TODO: Reemplazar con llamada real:
       *
       * const res = await fetch('/api/profesores', {
       *   method: 'POST',
       *   headers: {
       *     'Content-Type': 'application/json',
       *     'Authorization': `Bearer ${State.token}`
       *   },
       *   body: JSON.stringify({ nombre, email, password })
       * });
       * if (!res.ok) throw new Error('Error al registrar profesor');
       * const { data } = await res.json();
       * State.profesores.push(data);
       * ════════════════════════════════════════
       */

      // SIMULACIÓN: crear objeto local
      await new Promise(resolve => setTimeout(resolve, 500));

      const newProf = {
        id:        State.nextProfId++,
        nombre,
        email,
        createdAt: new Date(),
      };
      State.profesores.push(newProf);

      // Actualizar select de cursos con el nuevo profesor
      this.syncProfesoresSelect();

      // Limpiar form
      ['prof-nombre', 'prof-email', 'prof-password'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.value = '';
      });

      Helpers.showAlert('prof-success', null, '', 'success');
      this.renderTable();

    } catch (err) {
      Helpers.showAlert('prof-error', 'prof-error-msg', err.message || 'Error inesperado.');
    } finally {
      btn.disabled = false;
      btn.querySelector('.btn__label').textContent = 'Registrar Profesor';
    }
  },

  async fetchAll() {
    /**
     * ════════════════════════════════════════
     * TODO: Reemplazar con:
     *
     * const res = await fetch('/api/profesores', {
     *   headers: { 'Authorization': `Bearer ${State.token}` }
     * });
     * const { data } = await res.json();
     * State.profesores = data;
     * this.renderTable();
     * ════════════════════════════════════════
     */
    this.renderTable(); // usa datos en State
  },

  renderTable() {
    const tbody = document.getElementById('tbody-profesores');
    const stat  = document.getElementById('stat-profesores');
    if (!tbody) return;

    if (stat) stat.textContent = State.profesores.length;

    if (State.profesores.length === 0) {
      tbody.innerHTML = `
        <tr class="table-empty">
          <td colspan="5"><span class="mono">// Sin registros aún</span></td>
        </tr>`;
      return;
    }

    tbody.innerHTML = State.profesores.map((prof, i) => `
      <tr>
        <td class="mono" style="color: var(--c-text-muted)">${String(i + 1).padStart(2, '0')}</td>
        <td style="color: var(--c-text-primary); font-weight: 500">${this.escapeHtml(prof.nombre)}</td>
        <td class="mono" style="font-size: 12px">${this.escapeHtml(prof.email)}</td>
        <td><span class="badge-status badge-status--active">● activo</span></td>
        <td>
          <div class="action-btns">
            <button class="btn-action" onclick="Profesores.delete(${prof.id})">eliminar</button>
          </div>
        </td>
      </tr>
    `).join('');
  },

  delete(id) {
    /**
     * ════════════════════════════════════════
     * TODO: Reemplazar con:
     *
     * const res = await fetch(`/api/profesores/${id}`, {
     *   method: 'DELETE',
     *   headers: { 'Authorization': `Bearer ${State.token}` }
     * });
     * ════════════════════════════════════════
     */
    State.profesores = State.profesores.filter(p => p.id !== id);
    this.renderTable();
    this.syncProfesoresSelect();
  },

  /** Sincroniza los selects de Cursos con los profesores registrados */
  syncProfesoresSelect() {
    const select = document.getElementById('curso-profesor');
    if (!select) return;

    // Guardar opciones mock estáticas
    const staticOpts = `
      <option value="">— Selecciona un profesor —</option>
      <option value="mock-1">Dr. Ana Martínez (mock)</option>
      <option value="mock-2">Lic. Carlos Rueda (mock)</option>
      <option value="mock-3">Ing. Sofia Vela (mock)</option>
    `;

    const dynamicOpts = State.profesores.map(p =>
      `<option value="${p.id}">${Helpers.escapeHtml ? Helpers.escapeHtml(p.nombre) : p.nombre}</option>`
    ).join('');

    select.innerHTML = staticOpts + dynamicOpts;
  },

  escapeHtml(str) {
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
  },
};


/* ════════════════════════════════════════
   MÓDULO: CURSOS
════════════════════════════════════════ */
const Cursos = {
  thumbnailFile: null,
  thumbnailUrl: null,

  init() {
    document.getElementById('btn-add-curso')
      ?.addEventListener('click', () => this.handleAdd());

    document.getElementById('btn-refresh-cursos')
      ?.addEventListener('click', () => this.fetchAll());

    this.initFileDrop();
  },

  initFileDrop() {
    const dropArea  = document.getElementById('file-drop-area');
    const fileInput = document.getElementById('curso-thumbnail');
    const preview   = document.getElementById('file-preview');
    const content   = document.getElementById('file-drop-content');
    const previewImg = document.getElementById('preview-img');
    const removeBtn  = document.getElementById('btn-remove-file');

    if (!dropArea || !fileInput) return;

    // Drag & Drop events
    ['dragenter', 'dragover'].forEach(ev => {
      dropArea.addEventListener(ev, e => {
        e.preventDefault();
        dropArea.classList.add('drag-over');
      });
    });

    ['dragleave', 'drop'].forEach(ev => {
      dropArea.addEventListener(ev, e => {
        e.preventDefault();
        dropArea.classList.remove('drag-over');
      });
    });

    dropArea.addEventListener('drop', e => {
      const file = e.dataTransfer?.files[0];
      if (file) this.handleFile(file, preview, content, previewImg);
    });

    fileInput.addEventListener('change', () => {
      const file = fileInput.files[0];
      if (file) this.handleFile(file, preview, content, previewImg);
    });

    removeBtn?.addEventListener('click', e => {
      e.preventDefault();
      e.stopPropagation();
      this.thumbnailFile = null;
      this.thumbnailUrl  = null;
      fileInput.value    = '';
      preview.style.display  = 'none';
      content.style.display  = 'flex';
    });
  },

  handleFile(file, preview, content, previewImg) {
    // Validar tipo y tamaño
    if (!file.type.startsWith('image/')) {
      alert('Solo se permiten imágenes (PNG, JPG, WEBP).');
      return;
    }
    if (file.size > 2 * 1024 * 1024) {
      alert('La imagen no debe superar 2MB.');
      return;
    }

    this.thumbnailFile = file;
    const reader = new FileReader();
    reader.onload = e => {
      this.thumbnailUrl = e.target.result;
      previewImg.src = e.target.result;
      preview.style.display = 'block';
      content.style.display = 'none';
    };
    reader.readAsDataURL(file);
  },

  async handleAdd() {
    const nombre     = document.getElementById('curso-nombre')?.value.trim();
    const profesorId = document.getElementById('curso-profesor')?.value;
    const btn        = document.getElementById('btn-add-curso');

    Helpers.hideAlert('curso-error');
    Helpers.hideAlert('curso-success');

    if (!nombre) {
      Helpers.showAlert('curso-error', 'curso-error-msg', 'El nombre del curso es requerido.');
      return;
    }
    if (!profesorId) {
      Helpers.showAlert('curso-error', 'curso-error-msg', 'Selecciona un profesor.');
      return;
    }

    btn.disabled = true;
    btn.querySelector('.btn__label').textContent = 'Publicando...';

    try {
      /**
       * ════════════════════════════════════════
       * TODO: Reemplazar con:
       *
       * const formData = new FormData();
       * formData.append('nombre', nombre);
       * formData.append('profesorId', profesorId);
       * if (this.thumbnailFile) {
       *   formData.append('thumbnail', this.thumbnailFile);
       * }
       *
       * const res = await fetch('/api/cursos', {
       *   method: 'POST',
       *   headers: { 'Authorization': `Bearer ${State.token}` },
       *   body: formData   // No setear Content-Type; el browser lo pone con boundary
       * });
       * if (!res.ok) throw new Error('Error al crear curso');
       * const { data } = await res.json();
       * State.cursos.push(data);
       * ════════════════════════════════════════
       */

      await new Promise(resolve => setTimeout(resolve, 500));

      const newCurso = {
        id:           State.nextCursoId++,
        nombre,
        profesorId,
        thumbnailUrl: this.thumbnailUrl,
        createdAt:    new Date(),
      };
      State.cursos.push(newCurso);

      // Limpiar form
      document.getElementById('curso-nombre').value = '';
      document.getElementById('curso-profesor').value = '';
      document.getElementById('curso-thumbnail').value = '';
      this.thumbnailFile = null;
      this.thumbnailUrl  = null;
      document.getElementById('file-preview').style.display  = 'none';
      document.getElementById('file-drop-content').style.display = 'flex';

      Helpers.showAlert('curso-success', null, '', 'success');
      this.renderTable();

      // Actualizar stat counter
      const stat = document.getElementById('stat-cursos');
      if (stat) stat.textContent = State.cursos.length;

    } catch (err) {
      Helpers.showAlert('curso-error', 'curso-error-msg', err.message || 'Error inesperado.');
    } finally {
      btn.disabled = false;
      btn.querySelector('.btn__label').textContent = 'Publicar Curso';
    }
  },

  async fetchAll() {
    /**
     * ════════════════════════════════════════
     * TODO: Reemplazar con:
     *
     * const res = await fetch('/api/cursos', {
     *   headers: { 'Authorization': `Bearer ${State.token}` }
     * });
     * const { data } = await res.json();
     * State.cursos = data;
     * this.renderTable();
     * ════════════════════════════════════════
     */
    this.renderTable();
  },

  renderTable() {
    const tbody = document.getElementById('tbody-cursos');
    const stat  = document.getElementById('stat-cursos');
    if (!tbody) return;

    if (stat) stat.textContent = State.cursos.length;

    if (State.cursos.length === 0) {
      tbody.innerHTML = `
        <tr class="table-empty">
          <td colspan="5"><span class="mono">// Sin registros aún</span></td>
        </tr>`;
      return;
    }

    tbody.innerHTML = State.cursos.map((curso, i) => `
      <tr>
        <td class="mono" style="color: var(--c-text-muted)">${String(i + 1).padStart(2, '0')}</td>
        <td style="color: var(--c-text-primary); font-weight: 500">${this.escapeHtml(curso.nombre)}</td>
        <td style="font-size: 12px">${this.escapeHtml(Helpers.getProfesorNombre(curso.profesorId))}</td>
        <td>
          ${curso.thumbnailUrl
            ? `<img class="table-thumb" src="${curso.thumbnailUrl}" alt="thumb"/>`
            : `<div class="table-thumb-placeholder">⊘</div>`
          }
        </td>
        <td>
          <div class="action-btns">
            <button class="btn-action" onclick="Cursos.delete(${curso.id})">eliminar</button>
          </div>
        </td>
      </tr>
    `).join('');
  },

  delete(id) {
    /**
     * ════════════════════════════════════════
     * TODO: Reemplazar con:
     *
     * await fetch(`/api/cursos/${id}`, {
     *   method: 'DELETE',
     *   headers: { 'Authorization': `Bearer ${State.token}` }
     * });
     * ════════════════════════════════════════
     */
    State.cursos = State.cursos.filter(c => c.id !== id);
    this.renderTable();
  },

  escapeHtml(str) {
    const div = document.createElement('div');
    div.textContent = str || '';
    return div.innerHTML;
  },
};


/* ════════════════════════════════════════
   MÓDULO: ANALÍTICAS
════════════════════════════════════════ */
const Analiticas = {
  rendered: false,

  init() {
    // Rating input con preview en tiempo real
    const ratingInput = document.getElementById('rating-value');
    if (ratingInput) {
      ratingInput.addEventListener('input', () => this.updateStarPreview());
    }

    document.getElementById('btn-save-rating')
      ?.addEventListener('click', () => this.saveRating());

    // Inicializar preview
    this.updateStarPreview();
  },

  render() {
    if (this.rendered) return;
    this.rendered = true;
    this.renderRatingsList();

    /**
     * ════════════════════════════════════════
     * TODO: Al montar este módulo, fetchear datos reales:
     *
     * const [vistas, calificaciones] = await Promise.all([
     *   fetch('/api/analiticas/vistas', {
     *     headers: { 'Authorization': `Bearer ${State.token}` }
     *   }).then(r => r.json()),
     *   fetch('/api/analiticas/calificaciones', {
     *     headers: { 'Authorization': `Bearer ${State.token}` }
     *   }).then(r => r.json())
     * ]);
     *
     * renderVistasTable(vistas.data);
     * renderBarChart(vistas.data);
     * renderRatingsList(calificaciones.data);
     * ════════════════════════════════════════
     */
  },

  renderRatingsList() {
    const container = document.getElementById('ratings-list');
    if (!container) return;

    container.innerHTML = State.ratings.map(item => `
      <div class="rating-row">
        <span class="rating-row__name">${item.nombre}</span>
        <div class="rating-row__stars">
          <div class="stars">${Helpers.renderStars(item.rating)}</div>
          <span class="rating-value">${item.rating.toFixed(1)}</span>
        </div>
      </div>
    `).join('');
  },

  updateStarPreview() {
    const input   = document.getElementById('rating-value');
    const preview = document.getElementById('star-preview');
    if (!input || !preview) return;

    let val = parseFloat(input.value) || 0;
    // Asegurar rango válido y que sea múltiplo de 0.5
    val = Math.min(5, Math.max(0.5, Math.round(val * 2) / 2));

    preview.innerHTML = Helpers.renderStars(val) +
      `<span class="rating-value" style="margin-left: 8px">${val.toFixed(1)}</span>`;
  },

  async saveRating() {
    const cursoId = document.getElementById('rating-curso-select')?.value;
    const input   = document.getElementById('rating-value');
    const btn     = document.getElementById('btn-save-rating');

    if (!cursoId) {
      alert('Selecciona un curso para actualizar su calificación.');
      return;
    }

    let rating = parseFloat(input?.value);
    if (isNaN(rating) || rating < 0.5 || rating > 5.0) {
      alert('La calificación debe estar entre 0.5 y 5.0.');
      return;
    }
    // Forzar incrementos de 0.5
    rating = Math.round(rating * 2) / 2;

    btn.disabled = true;
    btn.querySelector('.btn__label').textContent = 'Guardando...';

    try {
      /**
       * ════════════════════════════════════════
       * TODO: Reemplazar con:
       *
       * const res = await fetch(`/api/cursos/${cursoId}/rating`, {
       *   method: 'PATCH',
       *   headers: {
       *     'Content-Type': 'application/json',
       *     'Authorization': `Bearer ${State.token}`
       *   },
       *   body: JSON.stringify({ rating })
       * });
       * if (!res.ok) throw new Error('No se pudo guardar');
       * ════════════════════════════════════════
       */

      await new Promise(resolve => setTimeout(resolve, 400));

      // Actualizar en State.ratings (mock)
      const idx = State.ratings.findIndex(r => r.cursoId == cursoId);
      if (idx !== -1) {
        State.ratings[idx].rating = rating;
      }

      this.renderRatingsList();
      alert(`Calificación actualizada: ${rating.toFixed(1)} ★`);

    } catch (err) {
      alert('Error al guardar calificación: ' + err.message);
    } finally {
      btn.disabled = false;
      btn.querySelector('.btn__label').textContent = 'Guardar calificación';
    }
  },
};


/* ════════════════════════════════════════
   NAVEGACIÓN DEL SIDEBAR
════════════════════════════════════════ */
const Sidebar = {
  init() {
    document.querySelectorAll('.nav-item').forEach(btn => {
      btn.addEventListener('click', () => {
        const module = btn.dataset.module;
        if (module) {
          Router.showModule(module);
          // Cerrar sidebar en mobile
          document.getElementById('sidebar')?.classList.remove('open');
        }
      });
    });

    // Toggle sidebar en mobile
    document.getElementById('menu-toggle')?.addEventListener('click', () => {
      document.getElementById('sidebar')?.classList.toggle('open');
    });

    // Logout
    document.getElementById('btn-logout')?.addEventListener('click', () => {
      this.logout();
    });
  },

  logout() {
    /**
     * ════════════════════════════════════════
     * TODO: Al implementar JWT real:
     *
     * localStorage.removeItem('jwt_token');
     * // Opcional: invalidar token en backend
     * await fetch('/api/auth/logout', {
     *   method: 'POST',
     *   headers: { 'Authorization': `Bearer ${State.token}` }
     * });
     * ════════════════════════════════════════
     */
    State.isAuthenticated = false;
    State.token = null;
    Router.showView('login');

    // Limpiar campos de login
    document.getElementById('login-email').value = '';
    document.getElementById('login-password').value = '';
  },
};


/* ════════════════════════════════════════
   INICIALIZACIÓN
════════════════════════════════════════ */
document.addEventListener('DOMContentLoaded', () => {
  // Mostrar vista de login por defecto
  Router.showView('login');

  // Inicializar todos los módulos
  Login.init();
  Profesores.init();
  Cursos.init();
  Analiticas.init();
  Sidebar.init();

  console.log('%c EduCore Admin ','background:#d4843a;color:#0a0b0d;font-weight:bold;padding:4px 8px;border-radius:4px;');
  console.log('%c JWT auth ready — ver app.js para los TODO de integración ','color:#8b91a0;font-size:11px;');
});

// Exponer en window para los onclick del HTML (tabla acciones)
window.Profesores = Profesores;
window.Cursos = Cursos;
