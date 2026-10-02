# Design System: Teen Driver Safety Tracker (Apex Telemetry & DrivePulse)

Extracted from Stitch Project: **Teen Driver Safety Tracker** (`projects/159422410397936659`)  
Target Device: **Mobile** | Theme: **Tactical High-Tech Minimalism & Functional Glassmorphism (Dark Mode)**

---

## 1. Brand Identity & Design Principles

The design bridges high-precision endurance racing instrumentation with modern, constructive safety coaching. It targets novice drivers, tech-forward parents, and performance commuters who value telemetry data, clarity, and habit reinforcement over surveillance.

* **Obsidian Canvas**: Eliminates glare during nighttime driving conditions while maximizing battery preservation on OLED mobile panels.
* **Cockpit Information Architecture**: Zero visual clutter, high-contrast hierarchy, and instantaneous glanceability at arm's length or in a dashboard phone mount.
* **Dynamic Status Feedback**:
  * **Electric Emerald**: Steady-state control, optimal velocity, smooth steering angle, high safety score.
  * **Caution Amber**: Threshold transitions, speed limit drift (+1 to +9%), dynamic curve advisory warnings.
  * **Vivid Coral-Red**: Critical telemetry spikes (excessive lateral G-forces, rapid deceleration, speed violations >10%).

---

## 2. Color Palette

### 2.1 Core Functional Roles

| Role | Color Name | Hex Code | Purpose & Usage |
| :--- | :--- | :--- | :--- |
| **Primary** | Electric Emerald | `#10B981` (`#4EDEA3`) | Optimal velocity, smooth steering, safe braking, score rings & active accents |
| **Secondary** | Caution Amber | `#F59E0B` (`#FFB95F`) | Threshold warnings, minor speed drift (+1-9%), curve advisories, passive coaching |
| **Tertiary / Danger** | Vivid Coral-Red | `#EF4444` (`#FFB3AD`) | Harsh braking, high lateral G-forces, severe overspeed (>10%), sensor disconnects |
| **Canvas Background** | Deep Obsidian | `#0B1326` (`#0F172A`) | Primary app substrate; infinite contrast against illuminated telemetry data |
| **Elevated Surfaces** | Slate Graphite | `#1E293B` (`#171F33`) | Structural card containers, module plates, telemetry docks |
| **Text Primary** | High-Luminance White | `#F8FAFC` (`#DAE2FD`) | Telemetry metric values, live speed numbers, critical headings |
| **Text Secondary** | Slate Gray | `#94A3B8` (`#BBCABF`) | Units of measurement (MPH, G), timestamps, sub-labels |
| **Borders & Dividers** | Subtle Slate Stroke | `rgba(148, 163, 184, 0.12)` | Hairline module delineation without adding visual clutter |

### 2.2 Semantic & Surface Palette Tokens (Material 3 Theme)

```yaml
colors:
  # Surfaces & Containers
  surface: '#0B1326'
  surface-dim: '#0B1326'
  surface-bright: '#31394D'
  surface-container-lowest: '#060E20'
  surface-container-low: '#131B2E'
  surface-container: '#171F33'
  surface-container-high: '#222A3D'
  surface-container-highest: '#2D3449'
  on-surface: '#DAE2FD'
  on-surface-variant: '#BBCABF'
  inverse-surface: '#DAE2FD'
  inverse-on-surface: '#283044'

  # Primary (Emerald Accent)
  primary: '#4EDEA3'
  primary-container: '#10B981'
  on-primary: '#003824'
  on-primary-container: '#00422B'
  primary-fixed: '#6FFBBE'
  primary-fixed-dim: '#4EDEA3'
  on-primary-fixed: '#002113'
  on-primary-fixed-variant: '#005236'
  inverse-primary: '#006C49'

  # Secondary (Amber Caution)
  secondary: '#FFB95F'
  secondary-container: '#EE9800'
  on-secondary: '#472A00'
  on-secondary-container: '#5B3800'
  secondary-fixed: '#FFDDB8'
  secondary-fixed-dim: '#FFB95F'
  on-secondary-fixed: '#2A1700'
  on-secondary-fixed-variant: '#653E00'

  # Tertiary / Error (Coral-Red Alert)
  tertiary: '#FFB3AD'
  tertiary-container: '#FF7A73'
  on-tertiary: '#68000A'
  on-tertiary-container: '#79000E'
  error: '#FFB4AB'
  error-container: '#93000A'
  on-error: '#690005'
  on-error-container: '#FFDAD6'

  # Outlines & Neutral
  outline: '#86948A'
  outline-variant: '#3C4A42'
  surface-tint: '#4EDEA3'
  background: '#0B1326'
  on-background: '#DAE2FD'
```

---

## 3. Typography

The type system cleanly distinguishes **human guidance & analytical narrative** from **high-frequency cockpit instrumentation**.

### 3.1 Font Families

| Role | Font Family | Fallbacks | Characteristics |
| :--- | :--- | :--- | :--- |
| **Headings & Display** | `Space Grotesk` | sans-serif | Technical, architectural, futuristic geometric sans |
| **Body & Narrative** | `Geist` | Inter, system-ui, sans-serif | Neutral, highly readable neo-grotesque for insights |
| **Telemetry & Metrics** | `JetBrains Mono` | monospace | Strict tabular monospace alignment to prevent jitter |

> **Note on Telemetry Metrics**: Always utilize OpenType tabular numerals (`font-feature-settings: 'tnum' 1;`) and slashed zeros where available to prevent horizontal jitter during rapid number fluctuations (e.g. speed, G-forces, RPM).

### 3.2 Typography Scale

| Token Name | Font Family | Size | Weight | Line Height | Letter Spacing | Target Elements |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `display-hero` | `JetBrains Mono` | 56px | 700 (Bold) | 60px | `-0.04em` | Large cockpit HUD speedometers |
| `display-hero-mobile`| `JetBrains Mono` | 44px | 700 (Bold) | 48px | `-0.03em` | Primary mobile driving metric |
| `headline-lg` | `Space Grotesk` | 32px | 600 (SemiBold)| 40px | `-0.02em` | Screen titles, summary cards |
| `headline-lg-mobile`| `Space Grotesk` | 26px | 600 (SemiBold)| 32px | `-0.02em` | Mobile screen titles |
| `headline-md` | `Space Grotesk` | 22px | 600 (SemiBold)| 28px | `-0.01em` | Section headers, card titles |
| `headline-sm` | `Space Grotesk` | 18px | 500 (Medium) | 24px | `normal` | Subheaders, grouped module labels|
| `body-lg` | `Geist` | 16px | 400 (Regular)| 24px | `normal` | Lead paragraphs, coaching notes |
| `body-md` | `Geist` | 14px | 400 (Regular)| 20px | `normal` | General descriptions, log entries |
| `body-sm` | `Geist` | 12px | 400 (Regular)| 16px | `normal` | Timestamps, metadata, footnotes |
| `label-telemetry-lg`| `JetBrains Mono` | 20px | 600 (SemiBold)| 24px | `+0.02em` | Secondary dials, delta figures |
| `label-telemetry-sm`| `JetBrains Mono` | 12px | 500 (Medium) | 16px | `+0.05em` | Status badge labels, G-readings |
| `label-caps` | `JetBrains Mono` | 10px | 700 (Bold) | 12px | `+0.08em` | Metric units (MPH, SCORE, G-LAT)|

---

## 4. Layout, Spacing & Elevation

### 4.1 Spacing Scale

```yaml
spacing:
  space-xs: 0.25rem   # 4px  - Micro padding, tag margins
  space-sm: 0.5rem    # 8px  - Compact pacing, inner icon gaps
  space-md: 1.0rem    # 16px - Standard component gap & card padding
  space-lg: 1.5rem    # 24px - Section spacing
  space-xl: 2.0rem    # 32px - Layout blocks, bottom safe-zones
  margin: 1.25rem     # 20px - Desktop/tablet margins
  margin-mobile: 1.0rem # 16px - Mobile screen edge margins
  gutter: 1.0rem      # 16px - Column gutter
  gutter-mobile: 0.75rem # 12px - Mobile column gutter
```

### 4.2 Border Radii (Shapes)

* `rounded-sm`: `0.25rem` (4px) - Badges, pill indicators
* `rounded-DEFAULT`: `0.5rem` (8px) - Metric tiles, nested modules
* `rounded-md`: `0.75rem` (12px) - Standard card containers
* `rounded-lg`: `1.0rem` (16px) - Modal sheets, elevated docks
* `rounded-xl`: `1.5rem` (24px) - Floating HUD overlays
* `rounded-full`: `9999px` - Action pills, radial gauge meters

### 4.3 Optical Luminescence & Glassmorphism

Rather than traditional drop shadows, depth is achieved through **luminescence, dark backplate tinting, and edge illumination**:

* **Layer 0 (Canvas Base)**: Deep obsidian `#0B1326`, matte and absorbent.
* **Layer 1 (Card & Module Deck)**: `#1E293B` at 70% opacity with `backdrop-filter: blur(12px)` and a 1px border of `rgba(148, 163, 184, 0.1)`.
* **Layer 2 (Floating Action Docks & HUD Widgets)**: `#1E293B` at 90% opacity with `border: 1px solid rgba(255, 255, 255, 0.15)`.
* **Telemetry Glow Overlays**:
  * *Stable State*: Ambient radial glow: `box-shadow: 0 0 24px rgba(16, 185, 129, 0.25);`
  * *Alert/Breach State*: Boundary glow: `box-shadow: 0 0 32px rgba(239, 68, 68, 0.35);`

---

## 5. Key UI Components

### 5.1 Telemetry Radial Rings & Speedometers
* **Structure**: Concentric SVG arcs with base track (`rgba(148, 163, 184, 0.15)`) and active glowing gauge track.
* **Center Well**: Big tabular metric (`display-hero-mobile`), stacked over a unit label (`label-caps`, e.g., `MPH`, `G-LAT`, `SCORE`).
* **Transitions**: Smooth color shifting (`#10B981` ➔ `#F59E0B` ➔ `#EF4444`) with 200ms cubic-bezier transitions.

### 5.2 Metric Cards
* **Container**: `#1E293B` with 1px stroke (`rgba(148, 163, 184, 0.12)`).
* **Header**: Top row with a micro icon, uppercase metric title (`label-caps`), and real-time pulsing 6px status dot.
* **Value**: Main tabular figure in `JetBrains Mono` (`headline-lg`) flanked by trend arrows and delta percentages.

### 5.3 Safety Badges & Event Tags
* Compact pills with 15% opacity background tint and 40% opacity border of the status color.
* Variants:
  * `Smooth Turn`: `#10B981` (Electric Emerald) with lateral G-reading (`0.22G`).
  * `Limit Advisory`: `#F59E0B` (Caution Amber) with delta (`+4 MPH`).
  * `Harsh Decel`: `#EF4444` (Vivid Coral-Red) with deceleration force (`-0.68G`).

### 5.4 Primary Action & Emergency Controls
* **Drive Action Primary**: Minimum 56px touch target, gradient base from `#10B981` to `#059669` with bold dark text (`#0F172A`) for daylight readability.
* **Hazard / SOS Action**: Ghost button with 1px stroke of `#EF4444`, coral text, and flashing emergency glow.
