<p align="center"><img src="https://raw.githubusercontent.com/DrakesCraft-Labs/SlimefunWarfare-Drake/main/banner.svg" alt="SlimefunWarfare banner" width="100%"></p>

# SlimefunWarfare for DrakesCraft

> ### 🏰 ¡Únete a la Comunidad Oficial de DrakesCraft!
> 
> * 🎮 **IP del Servidor**: `play.drakescraft.net` *(Java 1.21.11 & Bedrock)*
> * 💬 **Discord Oficial**: [discord.gg/drakescraft](https://discord.gg/rv3vtXZTk7)
> * 🌐 **Web & Guía**: [drakescraft.net](https://drakescraft.net) — 🛒 **Tienda**: [tienda.drakescraft.net](https://tienda.drakescraft.net)
> 
> *¡Juega con este addon y más de 80 expansiones optimizadas en vivo en nuestra network de supervivencia técnica!*

---

[![Build](https://github.com/DrakesCraft-Labs/SlimefunWarfare-Drake/actions/workflows/build.yml/badge.svg)](https://github.com/DrakesCraft-Labs/SlimefunWarfare-Drake/actions/workflows/build.yml)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-62b47a)
![Java](https://img.shields.io/badge/Java-21-e8c65b)

A maintained, production-hardened port of SlimefunWarfare for the Drake Slimefun core. It adds
military technology, rare-earth processing, chemical weapons and modular powered armor without
allowing those systems to bypass DrakesCraft's modality or protection boundaries.

## Highlights

- Firearms, energy weapons, ammunition and chemical grenades.
- Modular powered armor with movement, survival and defensive modules.
- Rare-earth ores, advanced alloys, meteor technology and processing machines.
- Stable original item IDs and recipes for data compatibility.
- Native Drake-core API and Java 21 / Minecraft 1.21.11 support.
- Explicit world allowlist and Slimefun protection checks.
- Non-destructive explosions; nuclear activation disabled by default.
- No external auto-updater and no GuizhanLib runtime dependency.

The complete progression and system reference lives in [docs/CONTENT.md](docs/CONTENT.md).

## Build

```bash
mvn --batch-mode clean verify
```

The production artifact is generated at `target/SlimefunWarfare.jar`.

## Runtime requirements

- Paper or Purpur 1.21.11
- Java 21
- Slimefun4-Drake `11.0-Drake-1.21.11-SNAPSHOT`

Review `safety.allowed-worlds` before deployment. The default configuration permits only the
three primary Slimefun dimensions and keeps every other modality isolated.

## Credits

Original concept and implementation by Seggan and upstream contributors. Drake maintenance,
compatibility work, Spanish in-game presentation and production hardening by Jack / DrakesCraft Labs.

This repository retains the original license in [LICENSE.txt](LICENSE.txt).

## Qué añade al juego

Addon de Slimefun con contenido propio: máquinas, objetos y recetas nuevas.
Registra alrededor de **100 objetos** en la guía de Slimefun.

Todo se fabrica y se investiga desde la guía normal (`/sf guide`), como cualquier otro contenido
de Slimefun: no hace falta ningún comando especial para empezar.

## Compatibilidad

| | |
|---|---|
| Servidor | Paper / Purpur **1.21.11** |
| Java | **21** |
| Requiere | [Slimefun4-Drake](https://github.com/DrakesCraft-Labs/Slimefun4-Drake) |
| Lado | Solo servidor — quien juega no instala nada |
| Versión | ${version} |

## Instalación

1. Descarga el `.jar` de la última versión.
2. Déjalo en la carpeta `plugins/` del servidor, junto a Slimefun.
3. Reinicia el servidor. Los objetos aparecen solos en la guía.

> Este addon está portado al fork de Slimefun de DrakesCraft. Con el Slimefun original puede no
> cargar, porque cambia el espacio de nombres de las clases.

## Créditos
- Seggan
- Jack

Port y mantenimiento por **DrakesCraft Labs**. La autoría original es de quien figura arriba; el detalle está en [docs/UPSTREAM_ATTRIBUTION.md](https://raw.githubusercontent.com/DrakesCraft-Labs/SlimefunWarfare-Drake/main/docs/UPSTREAM_ATTRIBUTION.md).

Licencia **GPL-3.0-only**.

## ⚖️ Upstream Attribution & License / Licencia y Créditos

- **Original Project / Upstream**: Slimefun4 Community Addon.
- **Port & Maintenance**: DrakesCraft Labs team (Compatibility for Paper / Purpur 1.21.11).
- **License**: GPL-3.0 / MIT.
- **Source Code**: [GitHub Repository](https://github.com/DrakesCraft-Labs/SlimefunWarfare-Drake)
- **Support & Issues**: [GitHub Issues](https://github.com/DrakesCraft-Labs/SlimefunWarfare-Drake/issues) | [Discord](https://discord.gg/rv3vtXZTk7)

*This project is an open-source derivative work maintained by DrakesCraft Labs under the terms of its original license. All original assets and concepts belong to their respective creators.*
