/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-without-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: explicit import has higher priority than star-import for same simple name
 */

// FILE: explicit.kt
package pkg1003.explicit

open class Target1003

// FILE: star.kt
package pkg1003.star

class Target1003

// FILE: use.kt
package pkg1003.use

import pkg1003.explicit.Target1003
import pkg1003.star.*

class User1003 : Target1003()

// TESTCASE NUMBER: 1
fun box(): String = if (User1003() is pkg1003.explicit.Target1003) "OK" else "NOK"
