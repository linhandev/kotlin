// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 4
 * DESCRIPTION: PACKAGE token in package declaration with file annotation before package; package name is preserved
 */

@file:Suppress("UNUSED")
package test.spec.keyword.p65.annotated

class PackageMarker65Annotated

// TESTCASE NUMBER: 1
fun box(): String {
    if (PackageMarker65Annotated::class.qualifiedName != "test.spec.keyword.p65.annotated.PackageMarker65Annotated") return "NOK"
    return "OK"
}
