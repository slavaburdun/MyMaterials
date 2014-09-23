CREATE TRIGGER DenyDelete
	ON Results
	FOR DELETE
AS
	IF (SELECT Schoolarship.Size FROM Schoolarship, DELETED WHERE DELETED.MarkID = Schoolarship.Mark) > 0
	BEGIN
		RAISERROR('You cannot delete session results, when schoolarship is applyed',16,1)
		ROLLBACK TRANSACTION
	END
	
	
CREATE TRIGGER UpdateHIGHACHIEVERS
	ON HIGHACHIEVERS
	INSTEAD OF UPDATE
AS
	DECLARE @StudentID INT,
			@NewMark INT,
			@Mark INT,
			@Semester INT,
			@StudentName VARCHAR(40),
			@StudentSurname VARCHAR(40),
			@ResultID INT
	SELECT TOP 1 @StudentName = INSERTED.Name FROM INSERTED
	SELECT TOP 1 @StudentSurname = INSERTED.Surname FROM INSERTED
	SELECT @StudentID = Students.ID FROM Students 
		WHERE 
			Students.Name = @StudentName AND
			Students.Surname = @StudentSurname
	SELECT TOP 1 @NewMark = INSERTED.MarkID FROM INSERTED
	SELECT TOP 1 @Mark = DELETED.MarkID FROM DELETED
	SELECT TOP 1 @Semester = INSERTED.Semester FROM INSERTED
	SELECT @ResultID = Results.ID FROM Results
		WHERE
			Results.Semester = @Semester AND
			Results.MarkID = @Mark AND
			Results.StudentID = @StudentID
		
	IF @StudentID > 0 BEGIN
		UPDATE Results SET Results.MarkID = @NewMark WHERE Results.StudentID = @StudentID AND Results.MarkID = @Mark AND Results.Semester = @Semester
	END
