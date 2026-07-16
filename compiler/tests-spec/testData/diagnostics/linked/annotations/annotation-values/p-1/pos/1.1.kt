// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation class may declare Int String enum nested annotation and Array<String> properties
 */

// TESTCASE NUMBER: 1
enum class Color17101 { RED, GREEN }

annotation class Nested17101(val tag: String)

annotation class Values17101(
    val count: Int,
    val label: String,
    val color: Color17101,
    val nested: Nested17101,
    val tags: Array<String>
)

@Values17101(
    count = 1,
    label = "ok",
    color = Color17101.RED,
    nested = Nested17101("nested"),
    tags = ["a", "b"]
)
class Holder17101
