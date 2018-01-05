## LawReader
Format argumentów: argumenty oddzielone spacją.

Pierwszy argument _konstytucja_ / _uokik_ określa przeglądany i parsowany dokument, na tej podstawie generowana jest ścieżka dostępu do plików (".\konstytucja.txt" lub ".\uokik.txt").

Drugi argument _treść_ / _spis_ określa sposób wyświetlania, odpowiednio pełna treść wybranych fragmentów lub ich spis treści.

Trzeci argument _całość_ / _element_ / _przedział_ określa wyświetlaną część dokumentu, _całość_ oznacza cały dokument, 
_element_ pojedynczy element (jak rozdiał czy sekcja) wraz ze składowymi, _przedział_ oznacza fragment dokumentu ograniczony z dwóch stron (np. przez dwa artykuły).

W przypadku wyboru opcji _element_ kolejne argumenty oddzielone spacjami oznaczają jaki fragment chcemy wyświetlić, 
przykładowo:
>konstytucja treść element "Roz. I" "Sek. 3." "Art. 1."

W przypadku opcji _przedział_ kolejne dwa argumenty określają brzegowe artykuły wyświetlanego fragmentu, np:
>uokik spis przedział "Art. 23c." "Art. 112."

Format podawania końcowych argumentów jest taki jak w spisach treści, więc można go łatwo sprawdzić podając np. odpowiednio
>uokik spis całość
