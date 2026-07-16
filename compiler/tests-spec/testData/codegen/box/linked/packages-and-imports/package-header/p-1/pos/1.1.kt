/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, package-header -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: files without package header belong to root package and access each other without import
 */

// FILE: helper.kt
fun rootPackageHelper1000(): String = "OK"

// FILE: box.kt

// TESTCASE NUMBER: 1
fun box(): String {
    val result = rootPackageHelper1000()
    return if (result == "OK") "OK" else "NOK: $result"
}
