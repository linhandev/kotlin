/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: read-only member property access a.x resolves to property getter
 */

class Box1145(val tag1145: String)

// TESTCASE NUMBER: 1
fun box(): String = if (Box1145("OK").tag1145 == "OK") "OK" else "NOK"
