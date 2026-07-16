// FIR_IDENTICAL
// LANGUAGE: +ForbidExposingLessVisibleTypesInInline
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-published-api -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: PublishedApi on internal declaration allows access from public inline function
 */

// TESTCASE NUMBER: 1
@PublishedApi
internal fun publishedInternal17721(): Int = 1

public inline fun publicInline17721(): Int = publishedInternal17721()

// TESTCASE NUMBER: 2
class Holder17721 {
    @PublishedApi
    internal fun publishedMethod(): Int = 2
}

public inline fun callPublished17721(holder: Holder17721): Int = holder.publishedMethod()
